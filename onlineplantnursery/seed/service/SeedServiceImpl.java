package com.ec.onlineplantnursery.seed.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.repository.SeedRepository;

@Service
public class SeedServiceImpl implements ISeedService{

	
	@Autowired
	SeedRepository repo;
	
	public SeedServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public SeedServiceImpl(SeedRepository repo) {
		super();
		this.repo = repo;
	}



	@Override
	@Transactional
	public Seed addSeed(Seed seed) {
		repo.save(seed);
		return seed;
	}

	@Override
	@Transactional
	public Seed updateSeed(Seed seed) {
		int seedId = seed.getSeedId();
		Optional<Seed> s = repo.findById(seedId);
		Seed s1 = null;
		if(s.isPresent()) {
			s1 = s.get();
			s1.setCommonName(seed.getCommonName());
			s1.setBloomTime(seed.getBloomTime());
			s1.setWatering(seed.getWatering());
			s1.setDifficultyLevel(seed.getDifficultyLevel());
			s1.setTemparature(seed.getTemparature());
			s1.setTypeOfSeeds(seed.getTypeOfSeeds());
			s1.setSeedsDescription(seed.getSeedsDescription());
			s1.setSeedsStock(seed.getSeedsStock());
			s1.setSeedsCost(seed.getSeedsCost());
			s1.setSeedsPerPacket(seed.getSeedsPerPacket());
			repo.save(s1);
		}
		
		return s1;
	}

	@Override
	@Transactional
	public Seed deleteSeed(Seed seed) {
		boolean isDeleted = false;
		int seedId = seed.getSeedId();
		Optional<Seed> s = repo.findById(seedId);
		if(s.isPresent()) {
			repo.delete(seed);
		}
		return seed;
	}

	@Override
	public Seed viewSeed(int seedId) throws SeedIdNotFoundException{
		Optional<Seed> s = repo.findById(seedId);
		if(s.isEmpty()) {
			throw new SeedIdNotFoundException(seedId);
		}
		return repo.findById(seedId).get();
	}

	@Override
	public Seed viewSeed(String commonName) {
		
		return repo.getSeedByCommonName(commonName);
	}

	@Override
	public List<Seed> viewAllSeeds() {
		return repo.findAll();
	}

	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed) {
		return repo.getSeedsByTypeOfSeed(typeOfSeed);
	}

}
