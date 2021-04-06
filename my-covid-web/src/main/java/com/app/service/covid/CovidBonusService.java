package com.app.service.covid;

import java.util.List;

import com.app.error.GeneralException;
import com.app.model.CovidCasesBonus;

public interface CovidBonusService {

	List<CovidCasesBonus> bonus() throws GeneralException;

	CovidCasesBonus addCovidBonus(String desc);

	int deleteCovidBonus(Long id) throws GeneralException;

	CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) throws GeneralException;

	CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus) throws GeneralException;

	List<CovidCasesBonus> deleteCovidBonus(String desc) throws GeneralException;

	List<String> findDuplicateNdelete() throws GeneralException;

	List<CovidCasesBonus> getCovidBonus();

}
