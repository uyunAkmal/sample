package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesBonus;

public interface CovidBonusService {

	List<CovidCasesBonus> bonus() throws Exception;

	List<CovidCasesArea> getCovid();

	List<CovidCasesBonus> getCovidBonus();

}