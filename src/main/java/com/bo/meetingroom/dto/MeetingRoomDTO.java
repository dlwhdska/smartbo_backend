package com.bo.meetingroom.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingRoomDTO {
	private Long id;
	private String name;
	private Integer maxNum;
	private Integer monitor;
	private Integer socket;
	private Integer projector;
	private Integer marker;
	private String location;
	
	private List<MeetingReservationDTO> reservation; //예약내역
}
