package com.app.mining.service.covid.api;

import java.util.List;

import com.app.model.CovidCasesArea;

public interface CovidMiningAPITotalCases {

	String doMining() throws Exception;

	String getTotalfromDB() throws Exception;

	List<CovidCasesArea> getLast5RecordsMYWithSize(int size);

	List<CovidCasesArea> getLast5RecordsMY();

	List<CovidCasesArea> getLast5RecordsWithParam(int size) throws Exception;

}
