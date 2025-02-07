package com.bo.notification.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.bo.exception.RemoveException;
import com.bo.notification.dto.NotificationDTO;
import com.bo.notification.service.NotificationServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/subscribe")
@CrossOrigin(origins="http://192.168.3.79:5173")
public class NotificationController {
	
	   private NotificationServiceImpl notifyService;
	   
	    // 생성자를 통한 의존성 주입
	    public NotificationController(NotificationServiceImpl notifyService) {
	        this.notifyService = notifyService;
	    }
	   
	   /**
	     * @title 로그인 한 유저 sse 연결
	     */
	    @GetMapping(value = "{id}", produces = "text/event-stream")
	    public SseEmitter subscribe(@PathVariable String id,
	                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
	    	
	    	log.warn("notification Controller id : {}", id);
	    	log.warn("notification RequestHeader lastEventId: {}", lastEventId);
	        return notifyService.subscribe(id, lastEventId);
	    }
	    
	    // 알림 조회
	    @GetMapping()
	    public List<NotificationDTO.Response> findAllByMemberId(@RequestParam String memberId) {
	        log.warn("Controller memberId ==> {}", memberId);
	        
	        return notifyService.findAllByMemberId(memberId);
	       
	    }
	    
		//***************** 메인 ***********************
	    /**
	     * 최근 9개의 알림 목록을 반환한다
	     * @param memberId
	     * @return
	     */
	    @GetMapping("/recent")
	    public List<NotificationDTO.Response> findRecentByMemberId(@RequestParam String memberId) {
	        log.warn("Controller memberId ==> {}", memberId);
	        
	        return notifyService.findRecntByMemberId(memberId);
	       
	    }
	    
	    // 알림 삭제
	    @DeleteMapping(value= "{id}", produces = "application/json;charset=UTF-8")
	    public ResponseEntity<?> deleteNotification(@PathVariable int id) throws RemoveException {
	    	
	    	try {
	    		
	    		notifyService.deleteNotification(id);
	    		return new ResponseEntity<>(HttpStatus.OK);
	    		
	    	} catch(RemoveException e) {
	    		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    	}
	    	
	    }
}
