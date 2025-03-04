package com.bo.car.dto;

import java.util.Date;

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
public class CarRentDTO {
	private Long id;
	private MemberDTO member;
	private CarDTO car;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date reqDate;
	
	private String startDate;
	
	private String endDate;
	
	@NotEmpty(message="대여 목적을 입력해야 합니다")
	@Size(max=50, message="최대 50자까지 작성할 수 있습니다")
	private String purpose;
	
	private Long status;
	
	@NotEmpty(message="반려 사유를 입력해야 합니다")
	@Size(max=50, message="최대 50자까지 작성할 수 있습니다")
	private String reject;
}
