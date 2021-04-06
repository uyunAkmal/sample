package com.app.mining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.error.GeneralException;
import com.app.mining.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MyCovidMiningController {

	public static final String MINING_MY_COVID = "/covid/mining/my";

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(MINING_MY_COVID)
	 public String mining() throws GeneralException {
		log.info("mining() started");
		String strReturn = null;

		try {
			covidMiningAPITotalCases.doMining();
			strReturn = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			
			log.error("mining() exception " + e.getMessage());
			throw new com.app.error.ControllerException(MINING_MY_COVID, e.getMessage());
		}

		log.info(MINING_MY_COVID + " return = {}" + strReturn);
		return strReturn;
	}

}
