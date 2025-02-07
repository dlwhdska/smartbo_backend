package com.bo.meetingroom.dto;

import com.bo.member.dto.MemberDTO;

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
public class ParticipantsDTO {
	private Long id;

	private MeetingReservationDTO meetingId;
	private MemberDTO member;	
}
