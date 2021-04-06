package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.entity.CovidCasesAreaEntity;
import com.app.entity.CovidCasesDescEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaDescMapper;
import com.app.mapper.CovidCasesAreaMapper;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesBonus;
import com.app.model.CovidCasesDesc;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.repository.covid.CovidCasesRepository;

import fr.xebia.extras.selma.Selma;

@Service
public class CovidServiceImpl implements CovidService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CovidServiceImpl.class);

	@Autowired
	CovidCasesRepository covidCasesRepository;

	@Autowired
	CovidCasesDescRepository covidCasesDescRepository;

	@Override
	public List<CovidCasesArea> getCovid() {
		log.info("getCovid started");
		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();
		List<CovidCasesAreaEntity> covidCaseEntities = covidCasesRepository.findAll();
		List<CovidCasesArea> covidCasesAreaList = new ArrayList<>();
		if (covidCaseEntities == null) {
			throw new IDNotFoundException(0L);
		} else {
			for (CovidCasesAreaEntity covidCasesEntity : covidCaseEntities) {
				CovidCasesArea covidCasesArea = mapper.asResource(covidCasesEntity);
				covidCasesAreaList.add(covidCasesArea);
				log.info("covidCasesEntity total Cases={}", covidCasesEntity.getCases());
			}
			log.info(" getCovid() return Size={}", covidCaseEntities.size());
		}
		return covidCasesAreaList;
	}

	@Override
	public List<CovidCasesDesc> getCovidDesc() {
		log.info("getCovidDesc started");
		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		List<CovidCasesDescEntity> covidCaseDescEntities = covidCasesDescRepository.findAll();
		List<CovidCasesDesc> covidCasesDescList = new ArrayList<>();
		if (covidCaseDescEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesDescEntity entity : covidCaseDescEntities) {
				CovidCasesDesc model = mapper.asResource(entity);
				covidCasesDescList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" getCovidDesc() return Size={}", covidCaseDescEntities.size());
		}

		return covidCasesDescList;

	}

	public CovidCasesDesc addCovid(@RequestParam(required = true) String desc) {
		log.info("addCovid() started={}", desc);

		CovidCasesDesc covidCasesDesc = null;

		CovidCasesDescEntity covidAreaDescEntity = new CovidCasesDescEntity();

		covidAreaDescEntity.setDescription(desc);

		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidAreaDescEntity);

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

		covidCasesDesc = mapper.asResource(savedEntity);

		return covidCasesDesc;

	}

	@Override
	public int deleteCovid(long id) {
		Optional<CovidCasesDescEntity> entityOptional = covidCasesDescRepository.findById(id);
		log.info("Entity found == {}", entityOptional.isPresent());
		if (entityOptional.isPresent()) {
			CovidCasesDescEntity covidAreaDescEntity = entityOptional.get();
			covidCasesDescRepository.delete(covidAreaDescEntity);
			return 1;
		} else
			return 0;
	}

	public CovidCasesDesc deleteCovid(String desc) throws com.app.error.ControllerException {

		// return should be the Saved CovidCasesDesc with values
		return null;
	}

	@Override
	public List<CovidCasesBonus> bonus() throws com.app.error.ControllerException {

		return null;
	}

	@Override
	public CovidCasesDesc putCovidDesc(CovidCasesDesc covidCasesDesc) {
		log.info("putCovid() started={}, covidCasesDesc={}", covidCasesDesc);

		CovidCasesDesc covidCasesdescSaved = null;

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

		CovidCasesDescEntity covidAreaDescEntity = mapper.asEntity(covidCasesDesc);

		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidAreaDescEntity);

		covidCasesdescSaved = mapper.asResource(savedEntity);
		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);

		return covidCasesdescSaved;
	}

	@Override

	public CovidCasesDesc postCovidDesc(CovidCasesDesc covidCasesDesc) {
		log.info("postCovidDesc() started={}, covidCasesDesc={}", covidCasesDesc);

		CovidCasesDesc covidCasesdescSaved = null;

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

		CovidCasesDescEntity covidAreaDescEntity = mapper.asEntity(covidCasesDesc);

		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidAreaDescEntity);

		covidCasesdescSaved = mapper.asResource(savedEntity);
		log.info("postCovid() ends, covidCasesDescSaved={}", covidCasesDesc);

		return covidCasesdescSaved;
	}

	@Override
	public int deleteCovidSoap(String desc) throws com.app.error.ControllerException {
		log.info("deleteCovidSoap() started desc={}", desc);

		// complete the implementation below
		return covidCasesDescRepository.deleteDescWithCondition(desc);

	}

	@Override
	public List<CovidCasesArea> addCovid() {

		return null;
	}
}
