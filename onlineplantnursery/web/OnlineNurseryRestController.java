package com.ec.onlineplantnursery.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ec.onlineplantnursery.exceptions.SeedIdNotFoundException;
import com.ec.onlineplantnursery.seed.entity.Seed;
import com.ec.onlineplantnursery.seed.service.SeedServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@RestController
@RequestMapping("/api")
@Api(value = "Online Nursery Application",description = "Customer can order planters")
public class OnlineNurseryRestController {

	Logger log = org.slf4j.LoggerFactory.getLogger(OnlineNurseryRestController.class);
	@Autowired
	SeedServiceImpl seedService;

	public OnlineNurseryRestController() {
		log.info("OnlineNurseryRestController -- constructor ");
		System.out.println("---->> Online Nursery Rest constructor");
	}
	
	@GetMapping("/home")
	public String homeRequest() {
		return "Welcome : Online Nurse Application" + LocalDateTime.now();
	}
	
	@ApiOperation(value = "seed post mapping" , response = Seed.class)
	@PostMapping("/seed")
	public Seed insertSeed(@RequestBody @Valid Seed s) {
		log.info("inside insert seeds");
		seedService.addSeed(s);
		return s;
	}
	
	@ApiOperation(value = "Seed Get mapping to fetch all seeds" , response = List.class)
	@GetMapping("/seeds")
	public List<Seed> getAllSeeds(){
		log.info("Get all seeds");
		return seedService.viewAllSeeds();
	}
	
	@ApiOperation(value = "Seed Get mapping to fetch seed by id" , response = Seed.class)
	@GetMapping("/seed/{id}")
	public Seed getSeedById(@PathVariable int id) throws SeedIdNotFoundException
	{
		log.info("Get seed information by id");
		return seedService.viewSeed(id);
		
	}
	
	@ApiOperation(value = "Seed Post mapping to delete seed" , response = Seed.class)
	@PostMapping("/seed/delete")
	public Seed deleteSeed(@RequestBody Seed seed)
	{
		log.info("Delete seed");
		return seedService.deleteSeed(seed);
		
	}
	
	@ApiOperation(value = "Seed Post mapping to delete seed" , response = Seed.class)
	@PutMapping("/seed/update")
	public Seed updateSeed(@RequestBody Seed seed)
	{
		return seedService.updateSeed(seed);
		
	}
	
	@ApiOperation(value = "Seed Get mapping to fetch details of seed by commonName" , response = Seed.class)
	@GetMapping("/seed/{commonName}")
	public Seed getSeedByCommonName(@PathVariable String commonName)
	{
		return seedService.viewSeed(commonName);
	}
	
	@ApiOperation(value = "Seed Get mapping to fetch all seeds by type of seed" , response = Seed.class)
	@GetMapping("/seeds/{typeOfSeed}")
	public List<Seed> getSeedsByTypeOfSeed(@PathVariable String typeOfSeed)
	{
		return seedService.viewAllSeeds(typeOfSeed);
	}
	
}
