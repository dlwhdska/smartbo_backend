package com.bo.stuff.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bo.exception.FindException;
import com.bo.stuff.dto.StuffDTO;
import com.bo.stuff.service.StuffServiceImpl;

@RestController
@RequestMapping("/stuff")
@CrossOrigin(origins="http://192.168.3.79:5173")
public class StuffController {
    @Autowired
    private StuffServiceImpl service;
    
    
    /**
     * 비품테이블의 전체 행을 조회하여 반환한다
     * @return List<StuffDTO>
     * @throws FindException
     */
    @GetMapping("/list")
    public List<StuffDTO> findAll() throws FindException{
    	return service.findAll();
    }
}
