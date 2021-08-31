package com.kollinchen.dojoandninja.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kollinchen.dojoandninja.models.Dojo;
import com.kollinchen.dojoandninja.models.Ninja;
import com.kollinchen.dojoandninja.repositories.DojoRepository;
import com.kollinchen.dojoandninja.repositories.NinjaRepository;

@Service
public class MainService {
	@Autowired
	private NinjaRepository ninjaRepo;
	
	@Autowired
	private DojoRepository dojoRepo;
	
	//DOJO
	public Dojo findDojo(Long id) {
		 return dojoRepo.findById(id).orElse(null);
	}
	public List<Dojo> allDojos(){
		return dojoRepo.findAll();
	}
	public Dojo saveDojo(Dojo dojo) {
		return dojoRepo.save(dojo);
	}

	//NINJA
	public List<Ninja> allNinjas(){
		return ninjaRepo.findAll();
	}
	public Ninja saveNinja(Ninja ninja) {
		return ninjaRepo.save(ninja);
	}
	public Ninja findNinja(Long id) {
		 return ninjaRepo.findById(id).orElse(null);
	}
}
