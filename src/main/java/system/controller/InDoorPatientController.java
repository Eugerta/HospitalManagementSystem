package system.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import system.model.Gender;
import system.model.InDoorPatient;
import system.model.Patient;
import system.repository.DoctorRepository;
import system.repository.InDoorPatientRepository;
import system.repository.PatientMedicationRepository;
import system.repository.PatientRepository;
import system.repository.RoomRepository;
import system.service.InDoorPatientService;
import system.repository.InDoorVisitScheduleRepository;
import system.repository.NurseRepository;

@RestController
public class InDoorPatientController {
	
	@Autowired
	private InDoorPatientRepository inDoorPatientRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private NurseRepository nurseRepository;
	
    @Autowired
	private RoomRepository roomRepository;
  
    @Autowired
    private PatientMedicationRepository pacientMedicationRepository;
    
    @Autowired
    private InDoorVisitScheduleRepository visitScheduleRepository;
    
    @Autowired
    private InDoorPatientService inDoorPatientService;
    
    private static final Logger logger = LogManager.getLogger(InDoorPatientController.class);
    
    private ModelAndView modelAndView = new ModelAndView();
	
	@RequestMapping(value = "/getAllInDoorPatient")
	public ModelAndView getAllPatients() {
		List<InDoorPatient> patients = inDoorPatientRepository.findAll();
		modelAndView.addObject("patients", patients);
		modelAndView.setViewName("patients/allpatient.html");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/getAllInDoorPatientDoc")
	public ModelAndView getAllInDoorPatientsDoc() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//System.out.println(auth.getName());
		List<InDoorPatient> patients = inDoorPatientRepository.getInDoorPatientByDoctor(auth.getName());
		//System.out.println(patients.toString());
		
		modelAndView.addObject("patients", patients);
		modelAndView.setViewName("patients/inDoorPatientDoc.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/getPatientByDoctor/{id}")
	public ModelAndView getAllPatientsByDoctor(@PathVariable String id) {
		List<InDoorPatient> patients = inDoorPatientRepository.getInDoorPatientByDoctor(id);
		modelAndView.addObject("patients", patients);
		modelAndView.setViewName("allpatientbydoctor.html");
		return modelAndView;
	}
	
	@PostMapping(value = "/addPatient")
	public ModelAndView addPatient(@Valid InDoorPatient patient, BindingResult result) {
		
		inDoorPatientService.save(patient);
		modelAndView.setViewName("redirect:/getAllInDoorPatientDoc?success=true");
		return modelAndView;
	}
	
	
	@PostMapping(value = "/addInPatientDoc")
	public ModelAndView addInPatientDoc(@Valid InDoorPatient patient, BindingResult result) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		patient.setDoctor(doctorRepository.findByUsername(auth.getName()).get());
		inDoorPatientService.save(patient);
		modelAndView.setViewName("redirect:/getAllInDoorPatientDoc?success=true");
		return modelAndView;
	}
	
	@RequestMapping(value = "/goToAddInPatientFormDoc")
	public ModelAndView goToAddInPatientFormDoc() {
		modelAndView.addObject("patient", new InDoorPatient());
		modelAndView.addObject("doctors",doctorRepository.findAll());
		Gender[] genders = Gender.values();
		modelAndView.addObject("genders", genders);
		modelAndView.addObject("rooms", roomRepository.findAll());
		modelAndView.addObject("nurses", nurseRepository.findAll());
		modelAndView.setViewName("patients/addPatientInDoc.html");
		return modelAndView;
	}
	
	@RequestMapping(value = "/goToEditPatientForm/{id}")
	public ModelAndView goToEditPatientForm(@PathVariable String id) {
		modelAndView.addObject("patient", inDoorPatientRepository.findById(id).get());
		modelAndView.addObject("doctors",doctorRepository.findAll());
		Gender[] genders = Gender.values();
		modelAndView.addObject("genders", genders);
		modelAndView.addObject("rooms", roomRepository.findAll());
		modelAndView.setViewName("editPatient.html");
		return modelAndView;
	}
	
	@PostMapping(value = "/editPatient/{id}")
	public ModelAndView editPatient(@PathVariable String id,@Valid InDoorPatient patient, BindingResult result) {
		inDoorPatientService.editInDoorPatient(id, patient);
		modelAndView.setViewName("redirect:/getAllInDoorPatient?edit=true");
		return modelAndView;
	}
	
	/*@RequestMapping(value = "/getAllPatient")
	public String getAllPatients1() {
		List<Patient> patients = patientRepository.findAll();
		modelAndView.addObject("patients", patients);

		return "allpatient.html";
	}
	*/

	@RequestMapping(value = "deletePatientRec/{id}")
	public ModelAndView deletePatientRec(@PathVariable String id ) {
		//pacientMedicationRepository.deleteMedicationByPatient(id);
		visitScheduleRepository.deleteVisitsByPatient(id);
		inDoorPatientRepository.deleteById(id);
		modelAndView.setViewName("redirect:/getAllInDoorPatientRec?delete=true");
		return modelAndView;
	}
	
	@RequestMapping(value = "deletePatientRecO/{id}")
	public ModelAndView deletePatient(@PathVariable String id ) {
		//pacientMedicationRepository.deleteMedicationByPatient(id);
		visitScheduleRepository.deleteVisitsByPatient(id);
		inDoorPatientRepository.deleteById(id);
		modelAndView.setViewName("redirect:/getAllOutDoorPatientRec?delete=true");
		return modelAndView;
		
	}
	
	@RequestMapping(value = "/goToAddPatientForm")
	public ModelAndView goToAddPatientForm() {
		modelAndView.addObject("patient", new Patient());
		modelAndView.addObject("doctors",doctorRepository.findAll());
		modelAndView.addObject("genders", Gender.values());
		modelAndView.addObject("rooms", roomRepository.findAll());
		modelAndView.setViewName("addPatient.html");
		return modelAndView;
	}
	
	
	
}
