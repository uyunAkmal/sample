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

import com.app.error.ControllerException;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.service.covid.CovidService;
import com.app.service.covid.CovidServiceImpl;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private static final String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private static final String GET_COVID = "/covid/get";

	private static final String GET_COVID_DESC = "/covid/get/desc";

	private static final String ADD_COVID = "/covid/add";

	private static final String DELETE_COVID = "/covid/delete";

	private static final String GET_HELLO_API = "/covid/hello";

	private static final String GET_LOG_API = "/covid/logging";

	private static final String PUT_API = "/covid/put";

	private static final String POST_API = "/covid/post";

	private static final String DELETE_COVID_SOAPUI = "/covid/delete/soap";

	@Autowired
	private CovidService covidService;

	@Autowired
	private CovidServiceImpl covidServiceImpl;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_LATEST_COVID_FROM_DB)
	public String getLatest() throws ControllerException {
		log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {

			log.error(" getLatest() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_LATEST_COVID_FROM_DB, e.getMessage());
		}

		log.info(GET_LATEST_COVID_FROM_DB + "  return = {}" + returnString);
		return returnString;
	}

	@GetMapping(GET_COVID_DESC)
	public List<CovidCasesDesc> findAllDesc() throws com.app.error.ControllerException {
		log.info("findAll() started");
		List<CovidCasesDesc> covidCasesdescs = null;
		try {
			covidCasesdescs = covidService.getCovidDesc();
		} catch (Exception e) {

			log.error(" findAll() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_COVID_DESC, e.getMessage());
		}

		log.info(GET_COVID_DESC + "  return = {}" + covidCasesdescs);
		return covidCasesdescs;
	}

	@GetMapping(GET_COVID)
	public List<CovidCasesArea> findAll() throws com.app.error.ControllerException {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {

			log.error(" findAll() exception " + e.getMessage());
			throw new com.app.error.ControllerException(GET_COVID, e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	@GetMapping(GET_HELLO_API)
	public String getHello() throws com.app.error.ControllerException {
		log.info("getHello() started");

		return "Hello API";
	}

	@GetMapping(GET_LOG_API)
	public String getLogging(@RequestParam String aNumberOnly) throws com.app.error.ControllerException {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);

		int numOnly = 0;
		if (aNumberOnly != null) {
			numOnly = Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + numOnly;
	}

	@GetMapping(ADD_COVID)
	public CovidCasesDesc addCovid(@RequestParam(required = true) String desc)
			throws com.app.error.ControllerException {
		log.info("addCovid() started={}", desc);

		CovidCasesDesc covidCasesDesc = null;
		try {
			covidCasesDesc = covidService.addCovid(desc);

		} catch (Exception e) {

			log.error("add() exception " + e.getMessage());
			throw new com.app.error.ControllerException(ADD_COVID, e.getMessage());
		}
		return covidCasesDesc;
	}

	@DeleteMapping(DELETE_COVID)
	public int deleteCovid(@RequestParam(required = true) long id) throws ControllerException {
		log.info("deleteCovid started");
		int i = 0;
		try {
			i = covidService.deleteCovid(id);
		} catch (Exception e) {
			// Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new ControllerException(DELETE_COVID, e.getMessage());
		}
		return i;
	}

	@PutMapping(PUT_API)
	public CovidCasesDesc putCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws ControllerException {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);

		// complete the implementation below
		CovidCasesDesc covidCasesdescSaved = null;
		try {
			covidCasesdescSaved = covidService.putCovidDesc(covidCasesDesc);
		} catch (Exception e) {

			log.error(" findAll() exception " + e.getMessage());
			throw new com.app.error.ControllerException(PUT_API, e.getMessage());
		}

		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);

		// return should be the Saved CovidCasesDesc with values
		return covidCasesdescSaved;
	}

	@PostMapping(POST_API)
	public CovidCasesDesc postCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws ControllerException {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesDesc);

		// complete the implementation below
		CovidCasesDesc covidCasesdescSaved = null;
		try {
			covidCasesdescSaved = covidService.postCovidDesc(covidCasesDesc);
		} catch (Exception e) {

			log.error(" findAll() exception " + e.getMessage());
			throw new com.app.error.ControllerException(POST_API, e.getMessage());
		}

		log.info("postCovid() ends, covidCasesDescSaved={}", covidCasesDesc);

		// return should be the Saved CovidCasesDesc with values
		return covidCasesdescSaved;
	}

	@DeleteMapping(DELETE_COVID_SOAPUI)

	public int deleteCovidSoap(@RequestParam(required = true) String desc) throws ControllerException {
		log.info("deleteCovidSoap() started desc={}", desc);

		// complete the implementation below
		int iDelete = covidServiceImpl.deleteCovidSoap(desc);
		log.info("deleteCovidSoap() ended iDelete = {}" + iDelete);
		return iDelete;
	}

}