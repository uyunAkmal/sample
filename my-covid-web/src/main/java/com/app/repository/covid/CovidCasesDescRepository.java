package com.app.repository.covid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;
import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long>, CovidCaseRepositoryCustom {
	
	@Query(value = "SELECT DISTINCT c.date, c.cases, c.id, c.fk_area_id FROM trx_covid_cases AS c order by date desc LIMIT 5", nativeQuery = true)
	List<CovidCasesAreaEntity> listLast5Records();

}
