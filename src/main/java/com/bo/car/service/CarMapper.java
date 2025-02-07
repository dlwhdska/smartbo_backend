package com.bo.car.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.bo.car.dto.CarDTO;
import com.bo.car.entity.CarEntity;

@Component
public class CarMapper {
	public CarEntity dtoToEntity(CarDTO dto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = dto;
		Class<CarEntity> destinationType = CarEntity.class;
		CarEntity entity = mapper.map(source, destinationType); //DTO->VO
		
		return entity;
	}
	
	public CarDTO entityToDto(CarEntity entity) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<CarDTO> destinationType = CarDTO.class;
		CarDTO dto = mapper.map(source, destinationType);
		
		return dto;
	}
	
	public CarDTO entityToDtoOptional(Optional<CarEntity> entity) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STANDARD)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<CarDTO> destinationType = CarDTO.class;
		CarDTO dto = mapper.map(source, destinationType);
		
		return dto;
	}
}
