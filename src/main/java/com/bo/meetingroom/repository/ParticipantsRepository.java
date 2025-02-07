package com.bo.meetingroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.meetingroom.entity.ParticipantsEntity;

public interface ParticipantsRepository extends JpaRepository<ParticipantsEntity, Long>{
	
	public List<ParticipantsEntity> findByMeetingId(Long meetingId);

}
