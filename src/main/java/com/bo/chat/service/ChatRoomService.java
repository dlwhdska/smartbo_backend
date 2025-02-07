package com.bo.chat.service;

import java.util.List;

import com.bo.chat.dto.ChatRoom;
import com.bo.exception.FindException;

public interface ChatRoomService {
	public List<ChatRoom> findAll() throws FindException;
}
