package com.bo.stuff.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bo.exception.AddException;
import com.bo.exception.FindException;
import com.bo.exception.ModifyException;
import com.bo.stuff.dto.StuffReqDTO;
import com.bo.stuff.service.StuffReqServiceImpl;
import com.bo.stuff.service.StuffServiceImpl;

@RestController
@RequestMapping("/stuff")
@CrossOrigin(origins="http://192.168.3.79:5173")
public class StuffReqController {
    @Autowired
    private StuffReqServiceImpl service;
    
    @Autowired
    private StuffServiceImpl serviceS;
    
    @PostMapping("/request")
    public void createStuffReq(@RequestBody StuffReqDTO dto) throws AddException {
        service.createStuffReq(dto);
    }
    
    @GetMapping("/requestlist")
    public List<StuffReqDTO> findByUserCase(@RequestParam String memberId,
                                            @RequestParam Long status,
                                            @RequestParam String stuffId,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws FindException {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return service.findByCase(memberId, status, stuffId, startDateTime, endDateTime);
    }
    
    @DeleteMapping("/request")
    public void removeById(@RequestParam Long id) {
        service.removeById(id);
    }
    
    @GetMapping("/waitproccess")
    public Long findWaitProccessCnt(String memberId) throws FindException {
        return service.findWaitProccessCnt(memberId);
    }
    
    @GetMapping("/requestmanage")
    public List<StuffReqDTO> findByMangeCase(@RequestParam Long departmentId,
                                             @RequestParam Long status,
                                             @RequestParam String stuffId,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) throws FindException {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return service.findByManageCase(departmentId, status, stuffId, startDateTime, endDateTime);
    }
    
    @GetMapping("/requestmanage/{id}")
    public StuffReqDTO findById(@PathVariable Long id) throws FindException {
        return service.findById(id);
    }
    
    @PutMapping("/approve")
    public void modifyReqApprove(@RequestBody StuffReqDTO dto) throws ModifyException {
        service.modifyReqApprove(dto);
        serviceS.modifyStock(dto);
    }
    
    @PutMapping("/reject")
    public void modifyReqReject(@RequestBody StuffReqDTO dto) throws ModifyException {
        service.modifyReqReject(dto);
    }
    
    @GetMapping("/unprocessedreq")
    public Long findUnprocessedCnt() throws FindException {
        return service.findUnprocessedReqCnt();
    }
}
