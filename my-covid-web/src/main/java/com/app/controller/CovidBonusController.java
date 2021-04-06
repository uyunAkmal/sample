package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.error.GeneralException;
import com.app.model.CovidCasesBonus;
import com.app.service.covid.CovidBonusService;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j

public class CovidBonusController {

	private static final String GET_MY_BONUS = "/covid/get/bonus";

	private static final String ADD_COVID_BONUS = "/covid/add/bonus";

	private static final String DELETE_COVID_BONUS = "/covid/delete/bonus";

	private static final String PUT_API_BONUS = "/covid/put/bonus";

	private static final String POST_API_BONUS = "/covid/post/bonus";

	private static final String DELETE_COVID_SOAPUI_BONUS = "/covid/deletesoap/bonus";

	private static final String FIND_DUPLICATE_DELETE_COVID = "/covid/delete/duplicate/bonus";

	@Autowired
	CovidBonusService covidBonusService;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	// retrieve data in table
	@GetMapping(GET_MY_BONUS)
	public List<CovidCasesBonus> bonus() throws GeneralException {
		List<CovidCasesBonus> covidCasesBonus = null;
		log.info("bonus() started");

		try {
			covidCasesBonus = covidBonusService.bonus();
			if (covidCasesBonus == null) {
				throw new GeneralException("No bonus yet");
			}
		} catch (Exception e) {

			log.error("bonus() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_MY_BONUS, e.getMessage());
		}

		log.info(GET_MY_BONUS + " return = {}" + covidCasesBonus);
		return covidCasesBonus;
	}

	// Add function

	@GetMapping(ADD_COVID_BONUS)
	public CovidCasesBonus addCovidBonus(@RequestParam(required = true) String desc) throws GeneralException {
		log.info("addCovid() started={}", desc);
		CovidCasesBonus covidCasesBonus = null;
		try {

			if (desc == null || desc.equals("undefined") || desc.equals("")) {
				throw new NullPointerException(ADD_COVID_BONUS + ", desc is null or empty");
			}
			covidCasesBonus = covidBonusService.addCovidBonus(desc);

		} catch (Exception e) {

			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(ADD_COVID_BONUS,e.getMessage());
		}

		return covidCasesBonus;
	}

	// Delete function
	@DeleteMapping(DELETE_COVID_BONUS)
	public int deleteCovidBonus(@RequestParam(required = true) long id) throws GeneralException {
		log.info("deleteCovid() started id={}", id);
		try {
			return covidBonusService.deleteCovidBonus(id);

		} catch (Exception e) {

			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(DELETE_COVID_BONUS,e.getMessage());
		}
	}

	// Update record using put request method
	@PutMapping(PUT_API_BONUS)
	public CovidCasesBonus putCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws GeneralException {

		return covidBonusService.putCovidBonus(covidCasesBonus);
	}

	@PostMapping(POST_API_BONUS)
	public CovidCasesBonus postCovidBonus(@RequestBody CovidCasesBonus covidCasesBonus) throws GeneralException {
		return covidBonusService.postCovidBonus(covidCasesBonus);
	}

	@DeleteMapping(DELETE_COVID_SOAPUI_BONUS)
	public List<CovidCasesBonus> deleteCovidSoapBonus(@RequestParam(required = true) String desc) throws GeneralException {
		return covidBonusService.deleteCovidBonus(desc);
	}

	@DeleteMapping(FIND_DUPLICATE_DELETE_COVID)
	public List<String> findDuplicateNdelete() throws GeneralException {
		log.info("findDuplicateNdelete() started");

		// complete the implementation below
		// ensure logic related to repo move to service implementation
		List<String> e = covidBonusService.findDuplicateNdelete();

		for (String s : e) {
			log.info("Duplicate value found on Description Table--->" + s);

			covidBonusService.deleteCovidBonus(s);

			log.info("Value Deleted--->" + s);
		}

		log.info("findDuplicateNdelete() ended");
		return e;
	}

}
