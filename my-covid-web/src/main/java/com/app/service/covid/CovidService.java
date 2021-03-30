package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesBonus;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesArea> addCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc) throws Throwable;
	
	CovidCasesDesc deleteCovid(String desc) throws Exception;

	List<CovidCasesBonus> bonus() throws Exception;
	
	CovidCasesDesc putCovidDesc(CovidCasesDesc covidCasesDesc)throws RuntimeException, Exception;

	CovidCasesDesc postCovidDesc(CovidCasesDesc covidCasesDesc) throws Exception;

	int deleteCovidSoap(String desc) throws Exception;


}
 