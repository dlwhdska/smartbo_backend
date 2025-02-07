package com.bo.meetingroom.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bo.exception.AddException;
import com.bo.exception.DuplicateException;
import com.bo.exception.FindException;
import com.bo.exception.RemoveException;
import com.bo.exception.UnavailableException;
import com.bo.meetingroom.dto.MeetingReservationDTO;
import com.bo.meetingroom.dto.MeetingRoomDTO;
import com.bo.meetingroom.dto.ParticipantsDTO;
import com.bo.meetingroom.service.MeetingroomServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/meetingroom")
@CrossOrigin(origins="http://192.168.3.79:5173")
@Slf4j
public class MeetingroomController {	
	
	@Autowired
	MeetingroomServiceImpl service;
	
	//--------회의실 예약에서 할일-----------

	@GetMapping("") //전체 리스트
	public List<MeetingRoomDTO> findByMeetingRoom(@RequestParam String meetingDate) throws FindException {
		
		log.warn("controller dd {}", meetingDate);
		
		return service.findByMeetingRoom(meetingDate);
	}
	
	@GetMapping("/{id}") //회의실 상세보기
	public Optional<MeetingRoomDTO> findByMeetingroomId(@PathVariable Long id) throws FindException {
		return service.findByMeetingroomId(id);
	}
	
	@GetMapping("/reservation/{id}") //예약 상세보기
	public Optional<MeetingReservationDTO> findByResId(@PathVariable Long id) throws FindException {
		return service.findByResId(id);
	}
	
	@PostMapping(value="", produces="application/json;charset=UTF-8") //회의실 예약하기(저장)
	public ResponseEntity<?> createMeetingReservation(@RequestBody MeetingReservationDTO mrdto) throws AddException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		Map<String, Object> map = new HashMap<>(); //응답내용

		try {
			service.createMeetingReservation(mrdto);
			map.put("message", "예약되었습니다!");
			return new ResponseEntity<>(map, headers, HttpStatus.OK);
		} catch (AddException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (UnavailableException e) {
			map.put("message", "해당 시간에 예약이 차있습니다");
			return new ResponseEntity<>(map, headers, HttpStatus.FORBIDDEN);
		} catch (DuplicateException e) {
			map.put("message", "같은 시간에 이미 예약이 되어있습니다");
			return new ResponseEntity<>(map, headers, HttpStatus.FORBIDDEN);
		}
	}
	
	//--------내 예약보기에서 할일-----------
	
	@GetMapping("/myreservation/{currentPage}")
	public Page<MeetingReservationDTO> findAllByMemberId(@PathVariable int currentPage, @RequestParam String memberId) throws FindException {
		currentPage -= 1;
		Pageable pageable = PageRequest.of(currentPage, 10);
		return service.findAllByMemberId(pageable, memberId);
	}
	
	@PostMapping(value="/myreservation", produces="application/json;charset=UTF-8") //특정 회의에 참여자 추가하기
	public ResponseEntity<?> createParticipants(@RequestBody List<ParticipantsDTO> pdtoli) throws AddException {
		try {
			service.createParticipants(pdtoli);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<>("저장되었습니다", headers, HttpStatus.OK);			
		} catch (AddException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@DeleteMapping(value="/participants/{id}", produces="application/json;charset=UTF-8") //특정 회의의 참여자 제거하기
	public ResponseEntity<?> removeParticipants(@PathVariable Long id) {
		try {
			service.removeParticipants(id);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<>("참여자가 삭제되었습니다", headers, HttpStatus.OK);			
		} catch (RemoveException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@Transactional
	@DeleteMapping(value="/deletereservation/{id}", produces="application/json;charset=UTF-8") //회의 제거하기
	public ResponseEntity<?> removeMeeting(@PathVariable Long id) {
		try {
			service.removeMeeting(id);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "text/html; charset=UTF-8");
			return new ResponseEntity<>("회의가 삭제되었습니다", headers, HttpStatus.OK);			
		} catch (RemoveException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
