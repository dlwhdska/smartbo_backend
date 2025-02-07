package com.bo.chat.service;

import com.bo.chat.dto.ChatMessage;

public interface ChatService {
	public boolean isRegisteredChannelTopic(String roomId);

	public void registerChannelTopic(String roomId);

	public void publish(ChatMessage chatMessage);
}
