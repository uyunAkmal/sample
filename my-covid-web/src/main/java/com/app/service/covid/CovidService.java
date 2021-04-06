package com.app.service.covid;

import java.util.List;

import com.app.error.GeneralException;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesBonus;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesArea> addCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc) throws GeneralException;

	int deleteCovid(long id) throws com.app.error.ControllerException;

	List<CovidCasesBonus> bonus() throws com.app.error.ControllerException;

	CovidCasesDesc putCovidDesc(CovidCasesDesc covidCasesDesc) throws GeneralException;

	CovidCasesDesc postCovidDesc(CovidCasesDesc covidCasesDesc) throws GeneralException;

	int deleteCovidSoap(String desc) throws com.app.error.ControllerException;

}
