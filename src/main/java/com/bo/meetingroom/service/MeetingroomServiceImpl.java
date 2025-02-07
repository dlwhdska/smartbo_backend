package com.bo.meetingroom.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bo.exception.AddException;
import com.bo.exception.DuplicateException;
import com.bo.exception.FindException;
import com.bo.exception.RemoveException;
import com.bo.exception.UnavailableException;
import com.bo.meetingroom.dto.MeetingReservationDTO;
import com.bo.meetingroom.dto.MeetingRoomDTO;
import com.bo.meetingroom.dto.ParticipantsDTO;
import com.bo.meetingroom.entity.MeetingReservationEntity;
import com.bo.meetingroom.entity.MeetingroomDetailEntity;
import com.bo.meetingroom.entity.ParticipantsEntity;
import com.bo.meetingroom.repository.MeetingReservationRepository;
import com.bo.meetingroom.repository.MeetingRoomRepository;
import com.bo.meetingroom.repository.ParticipantsRepository;
import com.bo.member.entity.MemberEntity;
import com.bo.notification.entity.NotificationEntity;
import com.bo.notification.service.NotificationServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MeetingroomServiceImpl implements MeetingroomService {
	
	// 찬석
	@Autowired
	NotificationServiceImpl notify;
	
	@Autowired
	MeetingRoomRepository meetingroom;
	
	@Autowired
	MeetingReservationRepository reservation;
	
	@Autowired
	ParticipantsRepository participants;
	
	@Autowired
	ReservationValidator validator;
	
	@Autowired
	EntityManager entityManager;
		
	@Override
	public List<MeetingRoomDTO> findByMeetingRoom(String meetingDate) throws FindException {
		List<MeetingroomDetailEntity> entity = meetingroom.findAllByMeetingRoom(meetingDate);
		List<MeetingRoomDTO> list = new ArrayList();
		MeetingroomMapper mapper = new MeetingroomMapper();
		
		//Vo->DTO
		for (MeetingroomDetailEntity mre : entity) {
			MeetingRoomDTO dto = mapper.Meetingroom_VoToDto(mre);
			System.out.println("++++" + dto.getReservation().get(0).getMeetingDate());
			list.add(dto);
		}
		return list;
	}

	@Override
	public Optional<MeetingRoomDTO> findByMeetingroomId(Long id) throws FindException {
		Optional<MeetingroomDetailEntity> entity = meetingroom.findById(id);
		MeetingroomMapper mapper = new MeetingroomMapper();
		return entity.map(mapper::Meetingroom_VoToDto);
	}

	@Override
	public Optional<MeetingReservationDTO> findByResId(Long id) throws FindException {
		Optional<MeetingReservationEntity> entity = reservation.findById(id);
		MeetingroomMapper mapper = new MeetingroomMapper();
		return entity.map(mapper::Reservation_VoToDto);
	}
	
	@Transactional
	@Override
	public void createMeetingReservation(MeetingReservationDTO msdto) 
			throws AddException, UnavailableException, DuplicateException, ParseException {
		//DTO->Vo
		MeetingroomMapper mapper = new MeetingroomMapper();
		MeetingReservationEntity entity = mapper.Reservation_DtoToVo(msdto);
		
		// 찬석(알림)
		MemberEntity memberEntity = entity.getMember();
		List<ParticipantsEntity> participantsEntity = entity.getParticipants();
		
		log.warn("1. memberENtity : {} " , memberEntity);
		
		String memberName = entity.getMember().getName();
			
		log.warn("2.  ttttttttttttt : {} => ", memberName);
		
		//최종
		if (!validator.isAvailable(entity)) { //시간 가능 여부 false일 때
			throw new UnavailableException();
		} else if (!validator.idResDupChk(entity)) { //동일한 시간대, 같은 id 예약 가능 false일 때
			throw new DuplicateException();
		} else {
			MeetingReservationEntity savedEntity = reservation.save(entity);
			System.out.println("끝 : savedEntity" + savedEntity.getId());
			
			// 찬석 - 알림
			notify.send(memberEntity, NotificationEntity.NotificationType.MEETING, "회의실예약이 되었습니다.");
			notify.sendToParticipants(participantsEntity, NotificationEntity.NotificationType.MEETING, memberName + "님이 회의실을 예약했습니다.");
		}
			
	}

	
	@Override
	public Page<MeetingReservationDTO> findAllByMemberId(Pageable pageable, String memberId) throws FindException {
		Page<MeetingReservationEntity> entity = reservation.findAllByMemberIdOrderByMeetingDateDesc(pageable, memberId);
		MeetingroomMapper mapper = new MeetingroomMapper();
		return entity.map(mapper::Reservation_VoToDto);
	}

	@Override
	public void createParticipants(List<ParticipantsDTO> pdtoli) throws AddException {		
		//DTO->Vo
		MeetingroomMapper mapper = new MeetingroomMapper();
		for (ParticipantsDTO pdto : pdtoli) {
			ParticipantsEntity pentity = mapper.Participants_DtoToVo(pdto);			
			ParticipantsEntity savedEntity = participants.save(pentity);
		}
	}

	@Override
	public void removeParticipants(Long id) throws RemoveException {
		try {
			participants.deleteById(id);
		} catch (Exception e) {
			throw new RemoveException();
		}
	}

	@Transactional
	@Override
	public void removeMeeting(Long id) throws RemoveException {
		try {
			Optional<MeetingReservationEntity> mrentity = reservation.findById(id);
	        MeetingReservationEntity entity = mrentity.get();
			System.out.println(")))))))" + id);
			
			//영속성 제거 (자식 엔터티와의 연관관계를 해제한다)
			entity.getParticipants().clear();
			entityManager.clear();
			
			reservation.delete(entity);		
		} catch (Exception e) {
			throw new RemoveException();
		}	
	}

}
