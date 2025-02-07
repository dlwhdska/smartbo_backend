package com.bo.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bo.chat.dto.ChatMessage;
import com.bo.chat.service.ChatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@CrossOrigin(origins = "http://192.168.3.79:5173")
public class ChatController {
	private final ChatService chatService;

	/**
	 * websocket에서 "/pub/chat/message"로 들어오는 메시징을 처리한다.
	 *
	 */
	@MessageMapping("/chat/message")
	// ws.send()에서 JSON.stringfy를 통해 자동으로 ChatMessage 형태로 반환된다.
	public void message(ChatMessage chatMessage) {
		// 등록이 안되어있다면 등록
		if (!chatService.isRegisteredChannelTopic(chatMessage.getRoomId())) {
			chatService.registerChannelTopic(chatMessage.getRoomId());
			System.out.println("message 등록");
		}
		if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장하셨습니다.");
			System.out.println("message 입장");
		}
		chatService.publish(chatMessage);
	}
}
