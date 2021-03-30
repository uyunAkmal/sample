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

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.repository.covid.CovidCasesRepository;
import com.app.service.covid.CovidService;
import com.app.service.covid.CovidServiceImpl;
import com.app.service.covid.api.CovidMiningAPITotalCases;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CovidController {

	private final static String GET_LATEST_COVID_FROM_DB = "/covid/get/latest";

	private final static String GET_COVID = "/covid/get";

	private final static String GET_COVID_DESC = "/covid/get/desc";

	private final static String ADD_COVID = "/covid/add";

	private final static String DELETE_COVID = "/covid/delete";

	private final static String GET_HELLO_API = "/covid/hello";

	private final static String GET_LOG_API = "/covid/logging";

	private static final String PUT_API = "/covid/put";

	private static final String POST_API = "/covid/post";

	private final static String DELETE_COVID_SOAPUI = "/covid/delete/soap";

	@Autowired
	private CovidService covidService;

	@Autowired
	private CovidCasesRepository covidCasesRepository;

	@Autowired
	private CovidServiceImpl covidServiceImpl;

	@Autowired
	private CovidCasesDescRepository covidCasesDescRepository;

	@Autowired
	CovidMiningAPITotalCases covidMiningAPITotalCases;

	@GetMapping(GET_LATEST_COVID_FROM_DB)
	String getLatest() throws Exception {
		log.info("getLatest() started");
		String returnString = null;

		try {
			returnString = covidMiningAPITotalCases.getTotalfromDB();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" getLatest() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_LATEST_COVID_FROM_DB + "  return = {}" + returnString);
		return returnString;
	}

	@GetMapping(GET_COVID_DESC)
	List<CovidCasesDesc> findAllDesc() throws Exception {
		log.info("findAll() started");
		List<CovidCasesDesc> covidCasesdescs = null;
		try {
			covidCasesdescs = covidService.getCovidDesc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID_DESC + "  return = {}" + covidCasesdescs);
		return covidCasesdescs;
	}

	@GetMapping(GET_COVID)
	List<CovidCasesArea> findAll() throws Exception {
		log.info("findAll() started");
		List<CovidCasesArea> covidCasesAreas = null;
		try {
			covidCasesAreas = covidService.getCovid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info(GET_COVID + "  return = {}" + covidCasesAreas);
		return covidCasesAreas;
	}

	// TODO: Practical 1 - Complete the API below
	// It should return hello when you hit http://localhost:8081/covid/hello on
	// browser

	@GetMapping(GET_HELLO_API)
	String getHello() throws Exception {
		log.info("getHello() started");

		return "Hello API";
	}

	// TODO: Practical 2 - Capture the error message below from log file
	// It should return some error when you pass a string as parameter to the HTTP
	// get
	// Example, http://localhost:8081/covid/hello?aNumberOnly=string

	@GetMapping(GET_LOG_API)
	String getLogging(@RequestParam String aNumberOnly) throws Exception {
		log.info("getLogging() started, requestParamvalue={}", aNumberOnly);

		if (aNumberOnly != null) {
			Integer.parseInt(aNumberOnly);
		}
		return "you have input =>" + aNumberOnly;
	}

	// TODO: Practical 4 (Add)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "TODO: Practical 4 " on CovidServiceImpl
	@GetMapping(ADD_COVID)
	CovidCasesDesc addCovid(@RequestParam(required = true) String desc) throws Exception {
		log.info("addCovid() started={}", desc);

		CovidCasesDesc covidCasesDesc = null;
		try {
			covidCasesDesc = covidService.addCovid(desc);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("add() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return covidCasesDesc;
	}

	// TODO: Practical 4 (Delete)
	// Move the logic below under try/catch area to CovidServiceImpl
	// check out the remarks of "TODO: Practical 4 " on CovidServiceImpl
	@DeleteMapping(DELETE_COVID)
	int deleteCovid(@RequestParam(required = true) long id) throws Exception {
		log.info("deleteCovid() started id={}", id);

		return covidServiceImpl.deleteCovid(id);
	}

	// TODO: Angular Practical 7 - Full Stack Application for Covid Put HTTP
	@PutMapping(PUT_API)
	CovidCasesDesc putCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws Exception {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);

		// complete the implementation below
		CovidCasesDesc covidCasesdescSaved = null;
		try {
			covidCasesdescSaved = covidService.putCovidDesc(covidCasesDesc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);

		// return should be the Saved CovidCasesDesc with values
		return covidCasesdescSaved;
	}

	@PostMapping(POST_API)
	CovidCasesDesc postCovid(@RequestBody CovidCasesDesc covidCasesDesc) throws Exception {
		log.info("postCovid() started, covidCasesDesc={}", covidCasesDesc);

		// complete the implementation below
		CovidCasesDesc covidCasesdescSaved = null;
		try {
			covidCasesdescSaved = covidService.postCovidDesc(covidCasesDesc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(" findAll() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info("postCovid() ends, covidCasesDescSaved={}", covidCasesDesc);

		// return should be the Saved CovidCasesDesc with values
		return covidCasesdescSaved;
	}

	@DeleteMapping(DELETE_COVID_SOAPUI)

	int deleteCovidSoap(@RequestParam(required = true) String desc) throws Exception {
		log.info("deleteCovidSoap() started desc={}", desc);

		// complete the implementation below
		int iDelete = covidServiceImpl.deleteCovidSoap(desc);
		log.info("deleteCovidSoap() ended iDelete = {}" + iDelete);
		return iDelete;
	}
}
