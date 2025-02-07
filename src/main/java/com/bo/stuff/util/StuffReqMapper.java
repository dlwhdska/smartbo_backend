package com.bo.stuff.util;

import com.bo.department.dto.DepartmentDTO;
import com.bo.department.entity.DepartmentEntity;
import com.bo.member.dto.MemberDTO;
import com.bo.member.entity.MemberEntity;
import com.bo.stuff.dto.StuffDTO;
import com.bo.stuff.dto.StuffReqDTO;
import com.bo.stuff.entity.StuffEntity;
import com.bo.stuff.entity.StuffReqEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StuffReqMapper {
	
	public static StuffReqEntity dtoToEntity(StuffReqDTO dto) {

		StuffEntity se =  StuffMapper.dtoToEntity(dto.getStuff());
		
		MemberDTO md = dto.getMember();
		MemberEntity me =  MemberEntity.builder()
				  .id(md.getId())
				  .name(md.getName())
				  .password(md.getPassword())
				  .position(md.getPosition())
				  .tel(md.getTel())
				  .build();
		DepartmentDTO dd = md.getDepartment();
		
		if(dd != null && dd.getId() != null) {
			DepartmentEntity de = DepartmentEntity
					.builder()
					.id(dd.getId())
					.name(dd.getName())
					.build();
			me.setDepartment(de);
		}
		
		StuffReqEntity entity = StuffReqEntity.builder()
		                                      .id(dto.getId())
		                                      .stuff(se)
		                                      .member(me)
		                                      .quantity(dto.getQuantity())
		                                      .purpose(dto.getPurpose())
		                                      .build();
		return entity;
	}
	
	
	static public StuffReqDTO entityToDto(StuffReqEntity entity) {

		StuffEntity se = entity.getStuff();
        
        StuffDTO sd = StuffDTO
        		.builder()
        		.id(se.getId())
        		.name(se.getName())
        		.stock(se.getStock())
        		.build();
        
        MemberEntity me = entity.getMember();
        
        DepartmentEntity de = me.getDepartment();
       
        DepartmentDTO dd = DepartmentDTO
				.builder()
				.id(de.getId()) 
				.name(de.getName())
				.build();
        
        MemberDTO md = MemberDTO
				.builder()
				.id(me.getId())
				.department(dd)
				.name(me.getName())
				.password(me.getPassword())
				.position(me.getPosition())
				.tel(me.getTel())
				.build();

        StuffReqDTO dto = StuffReqDTO.builder()
				.id(entity.getId())
				.stuff(sd)
				.member(md)
				.reqDate(entity.getReqDate())
				.quantity(entity.getQuantity())
				.status(entity.getStatus())
				.purpose(entity.getPurpose())
				.reject(entity.getReject())
                .build();
		
		return dto;
	}
}
