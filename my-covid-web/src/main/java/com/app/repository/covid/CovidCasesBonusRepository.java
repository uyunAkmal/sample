package com.app.repository.covid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesBonusEntity;

//TODO: Practical bonus final
// complete this as JpaRepository

// hint: interface is similar to CovidCasesDescRepository
public interface CovidCasesBonusRepository extends JpaRepository<CovidCasesBonusEntity, Long> {


}
