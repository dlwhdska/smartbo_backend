package com.bo.schedule.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.bo.schedule.dto.ScheduleDTO;
import com.bo.schedule.entity.ScheduleEntity;

public class ScheduleMapper {
	
	public ScheduleEntity dtoToEntity(ScheduleDTO dto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = dto;
		Class<ScheduleEntity> destinationType = ScheduleEntity.class;
		ScheduleEntity entity = mapper.map(source, destinationType); //DTO->VO
		
		return entity;
	}
	
	public ScheduleDTO entityToDto(ScheduleEntity entity) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<ScheduleDTO> destinationType = ScheduleDTO.class;
		ScheduleDTO dto = mapper.map(source, destinationType);
		
		return dto;
	}
}
