package com.bo.address.controller;

import java.lang.module.FindException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.address.dto.AddressMemberDTO;
import com.bo.address.service.AddressService;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://192.168.3.79:5173")
public class AddressController {
	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@GetMapping("/members") // /address/members에 대한 매핑 추가
	public List<AddressMemberDTO> findAll() throws FindException {
		return addressService.findAll();
	}

}
