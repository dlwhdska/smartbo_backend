package com.bo.notice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bo.notice.entity.NoticeEntity;


public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>{
	
	public Page<NoticeEntity> findAllByOrderByRegdateDesc(Pageable pageable);
	
}
