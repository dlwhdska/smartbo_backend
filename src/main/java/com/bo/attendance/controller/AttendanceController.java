package com.bo.attendance.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bo.attendance.dto.AttendanceDTO;
import com.bo.attendance.service.AttendanceService;
import com.bo.exception.AddException;
import com.bo.exception.FindException;
import com.bo.exception.ModifyException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/attendance")
@CrossOrigin(origins="http://192.168.3.79:5173")
public class AttendanceController {

	@Autowired
	private AttendanceService service;
	
	@GetMapping("/all")
	public Page<AttendanceDTO> findByMemberEntity(@RequestParam String memberId, int currentPage) throws FindException {
		
	    log.warn("Controller memberId ==> {}", memberId);
	    
		currentPage -=1;
		Pageable pageable = PageRequest.of(currentPage, 10);
	    
	    return service.findAllByMemberId(memberId, pageable);
		
	} 


	@PostMapping()
	public ResponseEntity<?> createAttendance(@RequestBody AttendanceDTO dto) throws AddException {
		
		try {
			service.createAttendance(dto);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch(AddException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PutMapping()
	public ResponseEntity<?> modifyAttendance(@RequestBody AttendanceDTO dto) throws ModifyException {
	
		try {
			service.modifyAttendance(dto);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ModifyException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/date")
	public Page<AttendanceDTO> findAllByAttendanceDate(@RequestParam String memberId, String attendanceDate,int currentPage) throws FindException {	
		
		log.warn("Controller findAllByAttendanceDate : {}", attendanceDate);
		
		currentPage -=1;
		Pageable pageable = PageRequest.of(currentPage, 10);
	    
	    return service.findAllByAttendanceDate(memberId, attendanceDate, pageable);

	}
	
	@GetMapping("/today")
	public Optional<AttendanceDTO> findByMemberId(@RequestParam String memberId) throws FindException {
		
		
		return service.findByMemberId(memberId);
		
	} 
	
}
