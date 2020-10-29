package system.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import system.model.Doctor;
import system.model.User;
import system.repository.DoctorRepository;
import system.repository.InDoorPatientRepository;
import system.repository.RoleRepository;
import system.repository.UserRepository;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	private InDoorPatientRepository patientRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	 private static final Logger logger = LogManager.getLogger(DoctorServiceImpl.class);

	
	
	public DoctorServiceImpl(DoctorRepository doctorRepository, UserRepository userRepository) {
		super();
		this.doctorRepository = doctorRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Doctor> getAllDoctor(){
		List<Doctor> doctors = doctorRepository.findAll();
		
		return doctors;
		
	}

	@Override
	public String deleteDoctor(String doctorId) {
		//patientRepository.setDoctorToNull(doctorId);
		doctorRepository.deleteById(doctorId);
		userRepository.deleteById(doctorId);
		logger.info("Doctor with id :"+ doctorId + "is deleted");
		
		return "SUCCESS";
	}

	@Override
	public Doctor addDoctor(Doctor doctor) {
	   
		doctor.setPassword(bCryptPasswordEncoder.encode(doctor.getPassword()));
		
		logger.info("Doctor :"+ doctor.getFirstName()+" "+doctor.getLastName() + " is added");
		doctorRepository.save(doctor);
		User u = new User();
		u.setId(doctor.getId());
		u.setPassword(doctor.getPassword());
		u.setUsername(doctor.getUsername());
		u.setGender(doctor.getGender());
		u.setRole(roleRepository.findRoleByName("Doctor"));
		u.setFirstName(doctor.getFirstName());
		u.setLastName(doctor.getLastName());
		u.setActive(true);
		userRepository.save(u);
		
		logger.info("User"+ u.getFirstName()+" "+u.getLastName()+" in role of doctor is added :");

		return doctor;
	}

	@Override
	public boolean existDoctor(String username) {
		if (userRepository.findUserByUsername(username) != null) 
		return true;
		else return false;
	}

	@Override
	public Doctor editDoctor(String id,Doctor doctor) {
		
	 	Doctor oldDoctor = doctorRepository.findById(id).get();
		doctor.setUsername(oldDoctor.getUsername());
		doctor.setPassword(oldDoctor.getPassword());
		
		doctorRepository.save(doctor);
		
		logger.info("Doctor: "+ oldDoctor.getFirstName()+" "+ oldDoctor.getLastName()+" is edited" );
		return doctor;
	}

}
