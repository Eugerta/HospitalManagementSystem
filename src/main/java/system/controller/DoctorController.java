package system.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import system.exception.HospitalSystemServiceException;
import system.exception.MessaggeConstants;
import system.model.Department;
import system.model.Doctor;
import system.model.Gender;
import system.model.Specialisation;
import system.repository.DepartmentRepository;
import system.repository.DoctorRepository;
import system.repository.InDoorPatientRepository;
import system.repository.RoleRepository;
import system.repository.SpecialisationRepository;
import system.repository.UserRepository;
import system.service.DoctorService;
import system.service.DoctorServiceImpl;

@RestController
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private InDoorPatientRepository patientRepository;

	@Autowired
	private SpecialisationRepository specialisationRepository;

	@Autowired
	private final DoctorService doctorService;
	
   private static final Logger logger = LogManager.getLogger(DoctorController.class);

	private ModelAndView modelAndView = new ModelAndView();
	
	public DoctorController(DoctorService docService) {
		this.doctorService = docService;
	}

	@RequestMapping("/deleteDoctor/{doctorId}")
	public ModelAndView deleteDoctor(@PathVariable String doctorId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/getAllDoctors");
		doctorService.deleteDoctor(doctorId);
		return modelAndView;
	}

	@GetMapping(value = "/getAllDoctors")
	public ModelAndView getAllDoctors() {
		ModelAndView modelAndView = new ModelAndView();
		List<Doctor> doctors = doctorService.getAllDoctor();
		//model.addAttribute("doctors", doctors);
		modelAndView.addObject("doctors", doctors);
		modelAndView.setViewName("doctors/doctors.html");
		return modelAndView ;
	}

	@RequestMapping(value = "/goToAddDoctorForm")
	public ModelAndView goToAddDoctorForm() {
		ModelAndView modelAndView = new ModelAndView();
		List<Department> departments = departmentRepository.findAll();
		modelAndView.addObject("departments", departments);
		List<Specialisation> specialisations = specialisationRepository.findAll();
		modelAndView.addObject("specialisations", specialisations);
		Gender[] genders = Gender.values();
		modelAndView.addObject("genders", genders);
		modelAndView.addObject("doctor", new Doctor());
		modelAndView.setViewName("doctors/addDoctor.html");
		return modelAndView;
	}

	@RequestMapping(value = "/goToEditDoctorForm/{id}")
	public ModelAndView goToEditDoctorForm(@PathVariable String id, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("departments", departmentRepository.findAll());
		List<Specialisation> specialisations = specialisationRepository.findAll();
		modelAndView.addObject("specialisations", specialisations);
		Gender[] genders = Gender.values();
		modelAndView.addObject("genders", genders);
		modelAndView.addObject("doctor", doctorRepository.findById(id).get());
		modelAndView.setViewName("doctors/editDoctor.html");
		
		return modelAndView;
	}

	@PostMapping(value = "/editDoctor/{id}")
	public ModelAndView editDoctor(@PathVariable String id, @Valid Doctor doctor, BindingResult result, Model model) {
		modelAndView.setViewName("redirect:/getAllDoctors?edit=true");
		
	       doctorService.editDoctor(id, doctor);
		   modelAndView.setViewName("redirect:/getAllDoctors?edit=true");
		
		return modelAndView;
	}

	@PostMapping(value = "/addDoctor")
	public ModelAndView addDoctor(@Valid Doctor doctor, BindingResult result) {

		ModelAndView modelAndView = new ModelAndView();
		if (doctorService.existDoctor(doctor.getUsername())) {
			
			logger.error("User with username: " +doctor.getUsername()+ " already exist! Can not create a new one!");
			modelAndView.setViewName("redirect:/goToAddDoctorForm?error=true");
			return modelAndView ;
		}
		
		else {
			doctorService.addDoctor(doctor);
			modelAndView.setViewName("redirect:/getAllDoctors?success=true");
			return modelAndView;
		}
		
	}

}
