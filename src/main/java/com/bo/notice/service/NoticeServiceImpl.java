package com.bo.notice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bo.exception.FindException;
import com.bo.notice.dto.NoticeDTO;
import com.bo.notice.entity.NoticeEntity;
import com.bo.notice.repository.NoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeRepository notice;

	@Override
	public Page<NoticeDTO> findAll(Pageable pageable) throws FindException{
		Page<NoticeEntity> entity = notice.findAllByOrderByRegdateDesc(pageable);
		NoticeMapper nm = new NoticeMapper();
//		System.out.println("service" + entity.getContent().get(0).getMember().getName());
		return entity.map(nm::VoToDto_ModelMapper);
	}

	@Override
	public Optional<NoticeDTO> findById(Long id) throws FindException {
		Optional<NoticeEntity> entity = notice.findById(id);
		NoticeMapper nm = new NoticeMapper();
		return entity.map(nm::VoToDto_ModelMapper);
	}
	
}
