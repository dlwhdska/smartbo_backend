package com.bo.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.chat.dto.ChatRoom;
import com.bo.chat.repository.ChatRoomRepository;
import com.bo.exception.FindException;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {
	@Autowired
	private ChatRoomRepository chatRoomRepository;

	public List<ChatRoom> findAll() throws FindException {
		List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
		if (chatRooms.isEmpty()) {
			throw new FindException("방이 없습니다");
		}
		System.out.println("ChatRoomServiceImpl-findAll " + chatRooms);
		return chatRooms;
	}
}
