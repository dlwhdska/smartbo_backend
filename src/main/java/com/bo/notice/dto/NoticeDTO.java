package com.bo.notice.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.bo.member.dto.MemberDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class NoticeDTO {
	private Long id;
	private Long memberId;
	private MemberDTO member;
	@NotNull(message="제목을 입력해주세요")
	@Size(max=100, message="제목은 최대 100자 이내로 작성해주세요")
	private String title;
	@NotNull(message="내용을 입력해주세요")
	@Size(max=1000, message="내용은 1000자 이내로 작성해주세요")
	private String content;
	@JsonFormat(pattern="yy/MM/dd", timezone="Asia/Seoul")
	private Date regdate;

}
