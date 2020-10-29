package system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import system.model.Laborant;
import system.repository.LaborantRepository;

public class LaborantServiceImpl implements LaborantService{
	
	@Autowired
	LaborantRepository laborantRepository;

	@Override
	public List<Laborant> findAll() {
		
		return laborantRepository.findAll();
	}

	@Override
	public Laborant findById(String id) {
		
		return laborantRepository.findById(id).get();
	}

	@Override
	public String deleteById(String id) {
		
		laborantRepository.deleteById(id);
		return "SUCCESS";
	}

	@Override
	public Laborant save(Laborant laborant) {
		
		return laborantRepository.save(laborant);
	}
	

}
