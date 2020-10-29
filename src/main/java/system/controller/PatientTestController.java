package system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.InDoorPatient;
import system.model.PatientTest;
import system.repository.LaborantRepository;
import system.repository.LaboratorRepository;
import system.repository.PatientRepository;
import system.repository.PatientTestRepository;
import system.repository.TestRepository;

@Controller
public class PatientTestController {

	@Autowired
	private PatientTestRepository patientTestRepository;
	@Autowired
	private PatientRepository pacientRepository;
	@Autowired
	private LaborantRepository laborantRepository;
	@Autowired
	private LaboratorRepository laboratorRepository;
	@Autowired
	private TestRepository testRepository;
	
	@RequestMapping(value = "/getAllPatientTest")
	public String getAllTest(Model model) {
		model.addAttribute("patienttest", patientTestRepository.findAll());
		return "tests/patientTest.html";
	}
	
	@RequestMapping(value = "/getAllPatientTestRec")
	public String getAllTestRec(Model model) {
		model.addAttribute("patienttest", patientTestRepository.findAll());
		return "tests/patientTestRec.html";
	}
	
	@RequestMapping(value = "/getAllPatientTestByPatient/{id}")
	public String getAllTestByPatient(@PathVariable String id,Model model) {
		model.addAttribute("patienttest", patientTestRepository.getTestByPatient(id));
		InDoorPatient patient = patientTestRepository.getTestByPatient(id).get(0).getInDoorPatient();
		model.addAttribute("patientName", patient.getFirstName()+ " "+ patient.getLastName());
		System.out.println( patient.getFirstName()+ " "+ patient.getLastName());
		return "tests/patientTestByPatient.html";
	}
	
	@RequestMapping(value = "/getAllPatientTestByLaborant/{id}")
	public String getAllTestByLaborant(@PathVariable String id,Model model) {
		model.addAttribute("patienttest", patientTestRepository.getTestByLaborant(id));
		return "tests/patientTestByLaborant.html";
	}
	
	@RequestMapping(value = "/getAllPatientTestByLaborator/{id}")
	public String getAllTestByLaborator(@PathVariable int id,Model model) {
		model.addAttribute("patienttest", patientTestRepository.getTestByLaborator(id));
		return "tests/patientTestByLaborator.html";
	}
	
	@RequestMapping(value = "/deletePatientTest/{id}")
	public String deletePatientTest(@PathVariable int id) {
		patientTestRepository.deleteById(id);
		return "redirect:/getAllPatientTest?delete=true";
	}
	
	@RequestMapping(value = "/goToAddPatientTestForm")
	public String goToAddTestPatientForm(Model model) {
		model.addAttribute("patientTest", new PatientTest());
		model.addAttribute("laborant", laborantRepository.findAll());
		model.addAttribute("laborator", laboratorRepository.findAll());
		model.addAttribute("patients", pacientRepository.findAll());
		model.addAttribute("tests", testRepository.findAll());
		return "tests/addPatientTest.html";
	}
	
	@PostMapping(value = "/addPatientTest")
	public String addPatientTest(@Valid PatientTest patientTest, BindingResult result, Model model) {
		//patientTest.setLaborant(patientTest.getLaborant());
		//patientTest.setLaborator(patientTest.getLaborator());
		patientTest.setInDoorPatient(patientTest.getInDoorPatient());
		patientTest.setTest(patientTest.getTest());
		patientTest.setId(patientTest.getId());
		patientTestRepository.save(patientTest);
		return "redirect:/getAllPatientTest?success=true";
	
	}
	
	
	@RequestMapping(value = "/goToAddPatientTestFormRec")
	public String goToAddTestPatientFormRec(Model model) {
		model.addAttribute("patientTest", new PatientTest());
		model.addAttribute("laborant", laborantRepository.findAll());
		model.addAttribute("laborator", laboratorRepository.findAll());
		model.addAttribute("patients", pacientRepository.findAll());
		model.addAttribute("tests", testRepository.findAll());
		return "addPatientTestRec.html";
	}
	
	@PostMapping(value = "/addPatientTestRec")
	public String addPatientTestRec(@Valid PatientTest patientTest, BindingResult result, Model model) {
		//patientTest.setLaborant(patientTest.getLaborant());
		//patientTest.setLaborator(patientTest.getLaborator());
		patientTest.setInDoorPatient(patientTest.getInDoorPatient());
		patientTest.setTest(patientTest.getTest());
		patientTest.setId(patientTest.getId());
		patientTestRepository.save(patientTest);
		return "redirect:/getAllPatientTestRec?success=true";
	}
}
