package com.bo.schedule.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.schedule.dto.ScheduleDTO;
import com.bo.schedule.entity.ScheduleEntity;
import com.bo.schedule.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	private ScheduleRepository sr;
	
	@Autowired
	public ScheduleServiceImpl(ScheduleRepository sr) {
		this.sr = sr;
	}
	
	@Override
	public List<ScheduleDTO> findAllSchedule(String memberId) {
		List<ScheduleEntity> entityList = sr.findAllByMemberIdOrderByStartTimeDesc(memberId);
		List<ScheduleDTO> dtoList = new ArrayList<>();
		for(ScheduleEntity entity : entityList) {			
			ScheduleMapper sm = new ScheduleMapper();
			ScheduleDTO dto = sm.entityToDto(entity);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@Override
	public void createSchedule(ScheduleDTO schedule) {
		ScheduleMapper sm = new ScheduleMapper();
		ScheduleEntity entity = sm.dtoToEntity(schedule);
		sr.save(entity);
	}
	
	@Override
	public void modifySchedule(ScheduleDTO schedule) {
		Optional<ScheduleEntity> optS = sr.findById(schedule.getId());
		ScheduleEntity scheduleEntity = optS.get();
		scheduleEntity.modifyScheduleTitle(schedule.getScheduleTitle());
		scheduleEntity.modifyContent(schedule.getContent());
		
		Timestamp Stimestamp = new Timestamp(schedule.getStartTime().getTime());
		Timestamp Etimestamp = new Timestamp(schedule.getEndTime().getTime());
		System.out.println("***********Stimestamp : "+Stimestamp);
		System.out.println("***********Etimestamp : "+Etimestamp);
		
		scheduleEntity.modifyStartTime(Stimestamp);
		scheduleEntity.modifyEndTime(Etimestamp);
		
		sr.save(scheduleEntity);
	}
	
	@Override
	public void removeByIdSchedule(Long id) {
		sr.deleteById(id);
	}
	
	@Override
	public List<ScheduleDTO> findAllTodaySchedule(String memberId){
		List<ScheduleEntity> entityList = sr.findAllTodaySchedule(memberId);
		List<ScheduleDTO> dtoList = new ArrayList<>();
		for(ScheduleEntity entity : entityList) {			
			ScheduleMapper sm = new ScheduleMapper();
			ScheduleDTO dto = sm.entityToDto(entity);
			dtoList.add(dto);
		}
		
		return dtoList;
	}
}
