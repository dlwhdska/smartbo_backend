package com.bo.attendance.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.bo.attendance.dao.AttendanceRepository;
import com.bo.attendance.dto.AttendanceDTO;
import com.bo.attendance.entity.AttendanceEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttendanceMapper {
	
	private AttendanceRepository repository;
	
	private ModelMapper mapper;
	
	public AttendanceMapper() {
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration()
				   .setMatchingStrategy(MatchingStrategies.STANDARD)
				   .setFieldAccessLevel(AccessLevel.PRIVATE)
				   .setFieldMatchingEnabled(true);
	}
	
	public AttendanceDTO VoToDTO(AttendanceEntity att) {

		log.warn("3. VoToDTO의 att =====> {}", att);
		
		Object source = att;

		AttendanceDTO dto = mapper.map(source, AttendanceDTO.class);
		
		return dto;

	} 

	public AttendanceEntity DtoToVo(AttendanceDTO dto) {
		
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
				   .setMatchingStrategy(MatchingStrategies.STANDARD)
				   .setFieldAccessLevel(AccessLevel.PRIVATE)
				   .setFieldMatchingEnabled(true);

		Object source = dto;
		Class<AttendanceEntity> destinationType=AttendanceEntity.class;

		AttendanceEntity entity = mapper.map(source, destinationType);

		log.warn("DtoToVO inner entity ======> {}", entity.getMemberId());
		
		return entity;
	}

}
