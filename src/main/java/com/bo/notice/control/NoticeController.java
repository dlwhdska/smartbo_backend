package com.bo.notice.control;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.exception.FindException;
import com.bo.notice.dto.NoticeDTO;
import com.bo.notice.service.NoticeServiceImpl;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins="http://192.168.3.79:5173")
public class NoticeController {
		
	@Autowired
	NoticeServiceImpl service;
	
	@GetMapping("/list") //공지사항 전체 리스트 조회
	public Page<NoticeDTO> findAll(@PageableDefault(size=10) Pageable pageable) throws FindException {
		return service.findAll(pageable);
	}
	
	@GetMapping("/{id}") //공지사항 상세 조회
	public Optional<NoticeDTO> findById(@PathVariable Long id) throws FindException{
		return service.findById(id);
	}
}
