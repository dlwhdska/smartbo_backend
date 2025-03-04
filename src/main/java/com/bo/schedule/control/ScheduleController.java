package com.bo.schedule.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.exception.AddException;
import com.bo.exception.FindException;
import com.bo.schedule.dto.ScheduleDTO;
import com.bo.schedule.service.ScheduleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/schedule")
@CrossOrigin(origins="http://192.168.3.79:5173")
public class ScheduleController {
	@Autowired
	private ScheduleService ss;
	
	@GetMapping("/calendar")
	public List<ScheduleDTO> findAllSchedule(String memberId) throws FindException{
		return ss.findAllSchedule(memberId);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createCarRent(@RequestBody ScheduleDTO schedule) throws AddException{
		System.out.println("*****memberId: "+schedule.getMember().getId());
		ss.createSchedule(schedule);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<?> modifySchedule(@RequestBody ScheduleDTO schedule){
		ss.modifySchedule(schedule);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> removeByIdSchedule(@PathVariable Long id) {
		ss.removeByIdSchedule(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/todaytodo")
	public List<ScheduleDTO> findByMemberIdTodaySchedule(String memberId) throws FindException{
		return ss.findAllTodaySchedule(memberId);
	}
}
