package system.service;

import java.util.List;

import system.model.Doctor;

public interface DoctorService {
	
	public List<Doctor> getAllDoctor();
	
	public Doctor addDoctor(Doctor doctor);
	
	public boolean existDoctor(String username);
	
	public Doctor editDoctor(String id, Doctor doctor);
	
	public String deleteDoctor(String doctorId);
	
	
	
	

}
