package com.ec.onlineplantnursery.seed.service;

import java.util.List;

import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.seed.entity.Seed;

public interface ISeedService {
	Seed addSeed(Seed seed);

	Seed updateSeed(Seed seed);

	Seed deleteSeed(Seed seed);

	Seed viewSeed(int seedId) throws SeedIdNotFoundException;

	Seed viewSeed(String commonName);

	List<Seed> viewAllSeeds();

	List<Seed> viewAllSeeds(String typeOfSeed);
}
