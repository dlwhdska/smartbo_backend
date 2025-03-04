package com.bo.meetingroom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bo.meetingroom.entity.MeetingroomDetailEntity;


public interface MeetingRoomRepository extends JpaRepository<MeetingroomDetailEntity, Long>{
	
	//회의실 전체 목록과 회의실별 예약 내역
	@Query(value="SELECT DISTINCT md.*\r\n"
			+ "FROM meetingroom_detail md\r\n"
			+ "LEFT JOIN meeting_reservation mr ON md.id = mr.meetingroom_id AND mr.meeting_date = :meetingDate\r\n"
			+ "WHERE mr.meeting_date IS NULL OR mr.meeting_date = :meetingDate\r\n"
			+ "ORDER BY md.name", nativeQuery=true)
	public List<MeetingroomDetailEntity> findAllByMeetingRoom(@Param("meetingDate") String meetingDate);
	
	//회의실 상세보기
	public Optional<MeetingroomDetailEntity> findById(Long id);

}
