package com.bo.stuff.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bo.department.entity.DepartmentEntity;
import com.bo.member.entity.MemberEntity;
import com.bo.stuff.entity.StuffEntity;
import com.bo.stuff.entity.StuffReqEntity;

@Repository
public interface StuffReqRepository extends JpaRepository<StuffReqEntity, Long> {
	
	// 비품요청내역 조회 ===========================================================================================================
	
//	/**
//	 * 멤버
//	 * stuff_req 테이블에서 memberId에 해당하는 행을 조회하여 반환한다
//	 * @param member
//	 * @return List<StuffReqEntity>
//	 */
//	List<StuffReqEntity> findByMember(MemberEntity member);
	
	/**
	 * 멤버, 요청상태
	 * @param member
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByMemberAndStatus(MemberEntity member, Long status);
	
	/**
	 * 멤버, 날짜, 요청상태
	 * stuff_req 테이블에서 멤버id, 날짜 구간, 특정 status에 해당하는 리스트를 반환한다.
	 * @param member
	 * @param startDate
	 * @param endDate
	 * @return <StuffReqEntity>
	 */
	List<StuffReqEntity> findByMemberAndReqDateBetweenAndStatusOrderByReqDateAsc(MemberEntity member, LocalDateTime startDate, LocalDateTime endDate, Long status);
	
	/**
	 * 멤버, 날짜
	 * stuff_req 테이블에서 멤버id, 날짜 구간에 해당하는 리스트를 반환한다.
	 * @param member
	 * @param startDate
	 * @param endDate
	 * @return <StuffReqEntity>
	 */
	List<StuffReqEntity> findByMemberAndReqDateBetweenOrderByReqDateAsc(MemberEntity member, LocalDateTime startDate, LocalDateTime endDate);
	
	
    /**
     * 멤버, 날짜, 요청상태, 비품분류
     * @param member
     * @param startDate
     * @param endDate
     * @param status
     * @param stuff
     * @return
     */
	List<StuffReqEntity> findByMemberAndReqDateBetweenAndStatusAndStuffLikeOrderByReqDateAsc(MemberEntity member, LocalDateTime startDate, LocalDateTime endDate, Long status, StuffEntity stuff);
	
    /**
     * 멤버, 날짜, 비품분류
     * @param member
     * @param startDate
     * @param endDate
     * @param startS
     * @param endS
     * @param stuff
     * @return
     */
	List<StuffReqEntity> findByMemberAndReqDateBetweenAndStuffLikeOrderByReqDateAsc(MemberEntity member, LocalDateTime startDate, LocalDateTime endDate, StuffEntity stuff);
	
	// 비품요청내역 조회 끝 ===========================================================================================================
	
	// 관리자용 비품요청내역 조회  ===========================================================================================================
	
	/**
	 * stuff_req 테이블에서 status 값에 따라 조회한다
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByStatus(Long status);
	
	/**
	 * stuff_req 테이블에서 status 값에 따라 조회하고 내림차순 정렬한다
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByStatusOrderByReqDateAsc(Long status);
	
	/**
	 * stuff_req 테이블에서 날짜 구간별 필터링
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<StuffReqEntity> findByReqDateBetweenOrderByReqDateAsc (LocalDateTime startDate, LocalDateTime endDate);

    /**
     * 날짜. 요청상태
     * @param startDate
     * @param endDate
     * @param status
     * @return
     */
	List<StuffReqEntity> findByStatusAndReqDateBetweenOrderByReqDateAsc (Long status, LocalDateTime startDate, LocalDateTime endDate);
	
	/**
	 * 날짜. 부서
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<StuffReqEntity> findByMember_DepartmentAndReqDateBetweenOrderByReqDateAsc (DepartmentEntity department, LocalDateTime startDate, LocalDateTime endDate);
	
	/**
	 * 날짜. 비품
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<StuffReqEntity> findByStuffLikeAndReqDateBetweenOrderByReqDateAsc (StuffEntity stuff, LocalDateTime startDate, LocalDateTime endDate);
	
	/**
	 * 날짜.요청.부서
	 * @param startDate
	 * @param endDate
	 * @param department
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByStatusAndMember_DepartmentAndReqDateBetweenOrderByReqDateAsc(Long status, DepartmentEntity department, LocalDateTime startDate, LocalDateTime endDate);
	
	
	/**
	 * 날짜.요청.비품
	 * @param startDate
	 * @param endDate
	 * @param department
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByStatusAndStuffLikeAndReqDateBetweenOrderByReqDateAsc(Long status, StuffEntity stuff, LocalDateTime startDate, LocalDateTime endDate);
	

	/**
	 * 날짜.부서.비품
	 * @param startDate
	 * @param endDate
	 * @param department
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByMember_DepartmentAndStuffLikeAndReqDateBetweenOrderByReqDateAsc(DepartmentEntity department, StuffEntity stuff, LocalDateTime startDate, LocalDateTime endDate);
	
	/**
	 * 날짜.요청. 부서.비품
	 * @param startDate
	 * @param endDate
	 * @param department
	 * @param status
	 * @return
	 */
	List<StuffReqEntity> findByMember_DepartmentAndStuffLikeAndStatusAndReqDateBetweenOrderByReqDateAsc(
			DepartmentEntity department, StuffEntity stuff, Long status, LocalDateTime startDate, LocalDateTime endDate);
	
}
