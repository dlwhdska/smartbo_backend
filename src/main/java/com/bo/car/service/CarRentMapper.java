package com.bo.car.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

import com.bo.car.dto.CarRentDTO;
import com.bo.car.entity.CarRentEntity;
import com.bo.car.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarRentMapper {
	public CarRentEntity dtoToEntity(CarRentDTO dto) {
		CarRepository cr;
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);

		Object source = dto;
		
		Class<CarRentEntity> destinationType = CarRentEntity.class;
		CarRentEntity entity = mapper.map(source, destinationType); //DTO->VO

		return entity;
	}
	
	public CarRentDTO entityToDto(CarRentEntity entity) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration()
		    .setMatchingStrategy(MatchingStrategies.STRICT)
			.setFieldAccessLevel(AccessLevel.PRIVATE)
			.setFieldMatchingEnabled(true);
		
		Object source = entity;
		Class<CarRentDTO> destinationType = CarRentDTO.class;
		CarRentDTO dto = mapper.map(source, destinationType);
		
		return dto;
	}
}
