package com.bo.notification.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.bo.exception.RemoveException;
import com.bo.meetingroom.entity.ParticipantsEntity;
import com.bo.member.entity.MemberEntity;
import com.bo.notification.dao.EmitterRepository;
import com.bo.notification.dao.NotificationRepository;
import com.bo.notification.dto.NotificationDTO;
import com.bo.notification.entity.NotificationEntity;
import com.bo.notification.entity.NotificationEntity.NotificationType;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {
	
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;
    // SSE 연결 지속 시간 설정

	@Autowired
	private EmitterRepository emitterRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private NotificationMapper model;
	
	
	// ======================= subscribe 요청 ==========================
	
	@Override
	public SseEmitter subscribe(String id, String lastEventId) {
		
		log.warn("Notification ServiceImpl id: {}", id);
		log.warn("Notification ServiceImpl lastEventId: {}", id);
		
		String emitterId = makeTimeIncludId(id);
		// 클라이언트의 sse연결 요청에 응답하기 위해서는 SseEmitter 객체를 만들어 반환해주어야 한다.
		SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
		// key값을 emitterId, 기본 타임아웃으로 default_timeout을 설정한 emitter 객체 생성
		emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));	// emitter가 완료될 때 'emitterId'에 해당하는 데이터를 repository에서 삭제
		emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));		// emitter가 타임아웃될 때 'emitterId'에 해당하는 데이터를 repository에서 삭제
		
		// 클라이언트가 미수신한 event 목록이 존재할 경우 이벤트를 재전송하여 event 유실을 예방 -> hasLostDate함수 사용
		if(hasLostData(lastEventId)) {
			sendLostData(lastEventId, id, emitterId, emitter);
		}
		
		return emitter;
		
	} 

	// emitterId를 설정할 메서드 생성
	private String makeTimeIncludId(String id) {
		
		return id + "_" + System.currentTimeMillis();
	}
	
	// 클라이언트에 sse 이벤트 전송을 위한 메서드 생성
	private void sendNotification(SseEmitter emitter, String eventId, String emitterId, Object data) {
		try {
				// 이벤트 전송
				emitter.send(SseEmitter.event().id(eventId).name("sse").data(data));
		} catch(IOException exception) {
			emitterRepository.deleteById(emitterId);
		} 
	} 
	
	private boolean hasLostData(String lastEventId) {
		return !lastEventId.isEmpty();
	} 
	
	// 클라이언트가 지난 이벤트를 제공했을 때(lastEventId), 해당 ID 이후에 발생한 이벤트 재전송 메서드 생성
	private void sendLostData(String lastEventId, String id, String emitterId, SseEmitter emitter) {
		// emitterRepository를 이용해서 해당 id에 모든 이벤트 캐시 가져옴
		Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(id));

	    for (Map.Entry<String, Object> entry : eventCaches.entrySet()) {
	        if (lastEventId.compareTo(entry.getKey()) < 0) {
	            String eventId = entry.getKey();
	            Object eventData = entry.getValue();
	            sendNotification(emitter, eventId, emitterId, eventData);
	        }
	    } 
	} 
 
	//  ===================== 리뷰어에게 알림 보내기 =======================
	
	@Override
	public void send(MemberEntity memberEntity, NotificationEntity.NotificationType notificationType, String content) {
		
		if(memberEntity == null) {
			log.warn("memberEntity가 null입니다");
			return;
		}
		
		// Notification 객체를 만들고, 현재 로그인 한 유저의 id값을 통해 SseEmitter를 모두 가져옴, 그 후 캐시에 저장해주고, 실제 데이터도 전송 해야한다
		 NotificationEntity notification = notificationRepository.save(createNotification(memberEntity, notificationType, content));
		
		 String receiver = memberEntity.getId();
		 String eventId = receiver + "_" + System.currentTimeMillis();
		 
		 // 로그인 한 유저의 SseEmitter 모두 가져오기
	     Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(receiver);
	     
	     for (Map.Entry<String, SseEmitter> entry : emitters.entrySet()) {
	    	    String key = entry.getKey();
	    	    SseEmitter emitter = entry.getValue();

	    	    // 데이터 캐시 저장(유실된 데이터 처리하기 위함)
	    	    emitterRepository.saveEventCache(key, notification);

	    	    // 데이터 전송
	    	    sendNotification(emitter, eventId, key, NotificationDTO.Response.createResponse(notification));    
	    	} // for
		
	} 
	
	// 회의 참가자들에게 알림 전송
    @Override
	public void sendToParticipants(List<ParticipantsEntity> participants, NotificationType notificationType, String content) {
		
    	if(participants == null) {
    		log.warn("회의참여자가 없습니다");
    		return;
    	}
    	
    	 for (ParticipantsEntity participant : participants) {
    	        MemberEntity member = participant.getMember();
    	        send(member, notificationType, content);
    	    }
    	 
	} 

    // 알림 생성
	private NotificationEntity createNotification(MemberEntity receiver, NotificationEntity.NotificationType notificationType, String content) { // (7)
        
		LocalDate currentDate = LocalDate.now();
		
		return NotificationEntity.builder()
                .receiverId(receiver) // 수신자
                .notificationType(notificationType)
                .content(content)
                .createdAt(currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .build();
    } 
    
    // 조회
    public List<NotificationDTO.Response> findAllByMemberId(String memberId) {
        List<NotificationEntity> notificationEntities = notificationRepository.findAllByMemberEntity(memberId);
        List<NotificationDTO.Response> notificationDTOs = new ArrayList<>();

        for (NotificationEntity entity : notificationEntities) {
            notificationDTOs.add(NotificationDTO.Response.createResponse(entity));
        }
        
        // id 필드를 기준으로 내림차순 정렬
        Collections.sort(notificationDTOs, Comparator.comparing(NotificationDTO.Response::getId).reversed());


        return notificationDTOs;
    } 

    
	@Override
	public List<NotificationDTO.Response> findRecntByMemberId(String memberId) {
        List<NotificationEntity> notificationEntities = notificationRepository.findAllByMemberEntity(memberId);
        List<NotificationDTO.Response> notificationDTOs = new ArrayList<>();

        for (NotificationEntity entity : notificationEntities) {
            notificationDTOs.add(NotificationDTO.Response.createResponse(entity));
        }
        
        // id 필드를 기준으로 내림차순 정렬
        Collections.sort(notificationDTOs, Comparator.comparing(NotificationDTO.Response::getId).reversed());
        
        // 처음부터 10개의 항목만 추출
        int endIndex = Math.min(9, notificationDTOs.size());
        List<NotificationDTO.Response> firstTenNotifications = notificationDTOs.subList(0, endIndex);


        return firstTenNotifications;
	}
    

	@Override
	public void deleteNotification(int id) throws RemoveException {
		
		notificationRepository.deleteById(id);
		
	}

}
