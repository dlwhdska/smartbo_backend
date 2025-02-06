package com.bo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bo.login.dto.LoginRequestDTO;
import com.bo.member.entity.MemberEntity;
import com.bo.member.repository.MemberRepository;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private MemberRepository memberRepository;

	public MemberEntity findByMemberId(LoginRequestDTO loginRequest) {
		MemberEntity member = memberRepository.findById(loginRequest.getId()).orElse(null);
		// findById 메서드는 Optional로 반환되기 때문에, .orElse(null)을 사용하여 null을 반환
		if (member != null && member.getPassword().equals(loginRequest.getPassword())) {
			return member;
		}
		return null;
	}
}
