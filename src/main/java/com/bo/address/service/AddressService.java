package com.bo.address.service;

import java.lang.module.FindException;
import java.util.List;

import com.bo.address.dto.AddressMemberDTO;

public interface AddressService {
	public List<AddressMemberDTO> findAll() throws FindException;

	public List<AddressMemberDTO> findPagedMembers(int page, int size);
}
