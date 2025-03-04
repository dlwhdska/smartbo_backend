package com.bo.stuff.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bo.member.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StuffReqDTO {
	private Long id;
	
	private StuffDTO stuff;
	
	private MemberDTO member;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	private LocalDateTime reqDate;
	
	private Long quantity;
	
	private Long status;
	
	@NotBlank(message="요청 사유를 입력하세요")
	@Size(max=50, message="최대 50자까지 작성할 수 있습니다")
	private String purpose;
	
	@NotBlank(message="반려 사유를 입력하세요")
	@Size(max=50, message="최대 50자까지 작성할 수 있습니다")
	private String reject;
}
