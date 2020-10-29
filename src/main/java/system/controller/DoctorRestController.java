package system.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system.exception.BaseResponseDto;
import system.exception.HospitalSystemServiceException;
import system.exception.MessaggeConstants;
import system.model.Department;
import system.model.Doctor;
import system.model.Gender;
import system.model.Specialisation;
import system.repository.DepartmentRepository;
import system.repository.DoctorRepository;
import system.repository.InDoorPatientRepository;
import system.repository.SpecialisationRepository;
import system.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorRestController {
	
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private  DoctorService doctorService;
	
	 private static final Logger logger = LogManager.getLogger(DoctorController.class);


	@DeleteMapping("/deleteDoctor/{doctorId}")
	public void deleteDoctor(@PathVariable String doctorId, Model model) {
		doctorService.deleteDoctor(doctorId);
	}

	@GetMapping("/getAllDoctors")
	public BaseResponseDto<List<Doctor>> getAllDoctors() {
		BaseResponseDto<List<Doctor>>  response = new BaseResponseDto<List<Doctor>>();
		List<Doctor> doctors = doctorService.getAllDoctor();
		
		return new BaseResponseDto<List<Doctor>>(new Date(), HttpStatus.OK.value(), null, MessaggeConstants.SERVICE_ELABORATED_CORRECT,doctors );
	}

	
	@PostMapping("/editDoctor/{id}")
	public BaseResponseDto<Doctor> editDoctor(@PathVariable String id, @Valid @RequestBody Doctor doctor) {
		
		doctorService.editDoctor(id, doctor);
		
		return new BaseResponseDto<Doctor>(new Date(), HttpStatus.OK.value(), null, MessaggeConstants.SERVICE_ELABORATED_CORRECT,doctor );
	}

	@PostMapping
	public BaseResponseDto<Doctor> addDoctor(@Valid @RequestBody Doctor doctor) {

		if (doctorService.existDoctor(doctor.getUsername())) {
			
			logger.error("User with username: " +doctor.getUsername()+ " already exist! Can not create a new one!");
			throw new HospitalSystemServiceException(MessaggeConstants.ERROR_USER_EXISTS);
		}
		
		else {
			doctorService.addDoctor(doctor);
			return new BaseResponseDto<Doctor>(new Date(), HttpStatus.OK.value(), null, MessaggeConstants.SERVICE_ELABORATED_CORRECT,doctor );

		}
		
	}
	
	@GetMapping("/getByUsername/{username}")
	public BaseResponseDto<Doctor> getByUsername(@PathVariable String username) {
		BaseResponseDto<Doctor>  response = new BaseResponseDto<Doctor>();
		Optional<Doctor>doctor = doctorRepository.findByUsername(username);
		
		if(doctor.isPresent())
		return new BaseResponseDto<Doctor>(new Date(), HttpStatus.OK.value(), null, MessaggeConstants.SERVICE_ELABORATED_CORRECT,doctor.get() );
	
		else throw new HospitalSystemServiceException(MessaggeConstants.ERROR_USER_EXISTS);

	}

}
