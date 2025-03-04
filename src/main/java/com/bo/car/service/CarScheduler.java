package com.bo.car.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CarScheduler {
	@Autowired
	private CarService cs;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void carStatusScheduler(){
		cs.modifyCarStatus();
	}
}
