package com.bo.schedule.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.bo.member.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
@ToString
public class ScheduleDTO {
	private Long id;
	private MemberDTO member;
	
	@NotEmpty(message="일정 제목을 입력해야 합니다")
	@Size(max=20, message="최대 20자까지 작성할 수 있습니다")
	private String scheduleTitle;
	
	@Size(max=50, message="최대 50자까지 작성할 수 있습니다")
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date startTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private Date endTime;
}
