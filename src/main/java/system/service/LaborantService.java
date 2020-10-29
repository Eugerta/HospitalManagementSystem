package system.service;

import java.util.List;

import system.model.Laborant;

public interface LaborantService {
	
	List<Laborant> findAll();
	
	Laborant findById(String id);
	
	String deleteById(String id);
	
	Laborant save(Laborant laborant);

}
