package com.app.repository.covid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;
import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository  extends JpaRepository<CovidCasesDescEntity, Long> {
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQL();

}
