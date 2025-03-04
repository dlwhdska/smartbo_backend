package com.bo.login.service;

import java.lang.module.FindException;

import com.bo.login.dto.LoginRequestDTO;
import com.bo.member.entity.MemberEntity;

public interface LoginService {
	public MemberEntity findByMemberId(LoginRequestDTO loginRequestDTO) throws FindException;
}
