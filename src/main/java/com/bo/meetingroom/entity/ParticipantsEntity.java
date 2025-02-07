package com.bo.meetingroom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.bo.member.entity.MemberEntity;

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

@Entity
@DynamicInsert
@Table(name="participants")
@SequenceGenerator(
		name = "PARTICIPANTS_SEQ_GENERATOR",
		sequenceName = "participants_seq",
		initialValue = 1,
		allocationSize = 1
		)
public class ParticipantsEntity {
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "PARTICIPANTS_SEQ_GENERATOR"
	)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="meetingId", nullable=false)
	private MeetingReservationEntity meeting;
	
	@ManyToOne
	@JoinColumn(name="memberId", nullable=false)
	private MemberEntity member;
	
}
