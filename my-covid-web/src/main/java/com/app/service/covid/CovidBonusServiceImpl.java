package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.GeneralException;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;

//complete this as Dependencies Injection Service

@Service
@Transactional
public class CovidBonusServiceImpl implements CovidBonusService {

	// hint
	// the method is similar to getCovidDesc() CovidServiceImpl file

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CovidBonusServiceImpl.class);

	@Autowired
	CovidCasesBonusRepository covidCasesBonusRepository;

	@Override
	public List<CovidCasesBonus> getCovidBonus() {
		log.info("getCovidBonus started");

		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {
			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("entity description={}", entity.getDescription());
			}
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}
		log.info("getCovidBonus ended --> covidCasesBonusList", covidCasesBonusList);
		return covidCasesBonusList;
	}

	@Override
	public CovidCasesBonus addCovidBonus(String desc) {

		// add codes below for adding it and modify it to suit with bonus table
		log.info("addCovidBonus started");

		CovidCasesBonus covidCasesBonus = null;
		CovidCasesBonusEntity covidCasesBonusEntity = new CovidCasesBonusEntity();

		covidCasesBonusEntity.setDescription(desc);

		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

		covidCasesBonus = mapper.asResource(savedEntity);

		return covidCasesBonus;

	}

	@Override
	public int deleteCovidBonus(Long id) {
		log.info("deleteCovidBonus started");

		Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

		log.info("Entity found == " + entityOptional.isPresent());

		if (entityOptional.isPresent()) {
			CovidCasesBonusEntity covidCasesBonusEntity = entityOptional.get();
			covidCasesBonusRepository.delete(covidCasesBonusEntity);
			return 1;
		}

		return 0;

	}

	@Override
	public CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		covidCasesBonus = mapper.asResource(savedEntity);

		log.info("putCovidBonys() ends, covidCasesBonysSaved={}", covidCasesBonus);
		return covidCasesBonus;

	}

	// post method
	public CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus) {
		log.info("postCovid() starts, covidCasesDesc={}", covidCasesBonus);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		CovidCasesBonusEntity covidCasesBonusEntity = mapper.asEntity(covidCasesBonus);
		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
		covidCasesBonus = mapper.asResource(savedEntity);

		log.info("postCovidBonus() end, covidCasesBonus={}", covidCasesBonus);
		return covidCasesBonus;

	}

	// delete by description
	@Override
	public List<CovidCasesBonus> deleteCovidBonus(String desc) throws GeneralException {

		log.info("deleteCovidSoap() started desc={}", desc);
		covidCasesBonusRepository.deleteBonusDescWithCondition(desc);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}

		return covidCasesBonusList;

	}

	@Override
	public List<String> findDuplicateNdelete() {
		log.info("findDuplicateNdelete() started");

		List<String> e = covidCasesBonusRepository.findDuplicate();

		for (String s : e) {
			covidCasesBonusRepository.deleteBonusDescWithCondition(s);
		}

		return e;
	}

	@Override
	public List<CovidCasesBonus> bonus() {

		return null;
	}

}
