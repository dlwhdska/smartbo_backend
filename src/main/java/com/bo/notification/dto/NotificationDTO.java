package com.bo.notification.dto;

import com.bo.notification.entity.NotificationEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class NotificationDTO {

	@Getter
	@Setter
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@ToString
	public static class Response {
		String id;
		String memberId;
		String content;
		String type;
		String createdAt;
		public static Response createResponse(NotificationEntity notify) {

			return Response.builder()
					.content(notify.getContent())
					.id(notify.getId().toString())
					.memberId(notify.getReceiverId().getId())
					.type(notify.getNotificationType().toString())
					.createdAt(notify.getCreatedAt())
					.build();
		}
	}
} // end class
