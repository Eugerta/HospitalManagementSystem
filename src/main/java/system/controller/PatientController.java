package system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Gender;
import system.model.Patient;
import system.repository.DepartmentRepository;
import system.repository.DoctorRepository;
import system.repository.PatientMedicationRepository;
import system.repository.PatientRepository;
import system.repository.RoomRepository;
import system.repository.InDoorVisitScheduleRepository;
import system.repository.OutDoorVisitScheduleRepository;

@Controller
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private PatientMedicationRepository pacientMedicationRepository;

	@Autowired
	private OutDoorVisitScheduleRepository visitScheduleRepository;

	@RequestMapping(value = "/getAllOutDoorPatientDoc")
	public String getAllPatientsOutDoc(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<Patient> patients = patientRepository.getOutDoorPatientByDoctor(auth.getName());
		model.addAttribute("patients", patients);
		return "patients/outpatientdoc.html";
	}

	@RequestMapping(value = "/getAllOutDoorPatient")
	public String getAllOutPatients(Model model) {
		List<Patient> patients = patientRepository.findAll();
		model.addAttribute("patients", patients);

		return "patients/allOutpatient.html";
	}

	@RequestMapping(value = "/goToAddOutPatientForm")
	public String goToAddOutPatientForm(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("doctors", doctorRepository.findAll());
		model.addAttribute("genders", Gender.values());
		return "patients/addOutPatient.html";
	}

	@RequestMapping(value = "/goToAddOutPatientFormDoc")
	public String goToAddOutPatientFormDoc(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("doctors", doctorRepository.findAll());
		model.addAttribute("genders", Gender.values());
		model.addAttribute("rooms", roomRepository.findAll());
		return "patients/addPatientOutDoc.html";
	}

	@PostMapping(value = "/addOutPatientDoc")
	public String addOutPatientDoc(@Valid Patient patient, BindingResult result, Model model) {
		if (patientRepository.findById(patient.getId()).isPresent()) {
			return "redirect:/goToAddOutPatientFormDoc?error=true";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		patient.setDoctor(doctorRepository.findById(auth.getName()).get());

		patientRepository.save(patient);
		return "redirect:/getAllOutDoorPatientDoc?success=true";
	}

	@RequestMapping(value = "/getOutPatientByDoctor/{id}")
	public String getAllPatientsByDoctor(@PathVariable String id, Model model) {
		List<Patient> patients = patientRepository.getPatientByDoctor(id);
		model.addAttribute("patients", patients);

		return "patients/allpatientbydoctor.html";
	}
	/*
	 * @RequestMapping(value = "/goToEditPatientForm/{id}") public String
	 * goToEditPatientForm(@PathVariable String id,Model model) {
	 * model.addAttribute("patient", patientRepository.findById(id).get());
	 * model.addAttribute("doctors",doctorRepository.findAll());
	 * model.addAttribute("genders", Gender.values()); return
	 * "patients/editPatient.html"; }
	 */

	@PostMapping(value = "/addOutPatient")
	public String addOutPatient(@Valid Patient patient, BindingResult result, Model model) {
		// if(patientRepository.findById(patient.getId()).isPresent()) {
		// return "redirect:/goToAddOutPatientForm?error=true";
		// }

		patientRepository.save(patient);
		return "redirect:/getAllOutDoorPatient?success=true";
	}

	@RequestMapping(value = "/goToEditOutPatientForm/{id}")
	public String goToEditOutPatientForm(@PathVariable String id, Model model) {
		model.addAttribute("patient", patientRepository.findById(id).get());
		model.addAttribute("doctors", doctorRepository.findAll());
		model.addAttribute("genders", Gender.values());
		model.addAttribute("rooms", roomRepository.findAll());
		return "patients/editOutPatient.html";
	}

	@PostMapping(value = "/editOutPatient/{id}")
	public String editOutPatient(@PathVariable String id, @Valid Patient patient, BindingResult result, Model model) {
		patient.setDoctor(patient.getDoctor());
		model.addAttribute("genders", Gender.values());
	
		patient.setId(id);
		patientRepository.save(patient);
		return "redirect:/getAllOutDoorPatientDoc?edit=true";
	}
	
	 @RequestMapping(value = "deleteOutPatient/{id}") 
	 public String deleteOutPatient(@PathVariable String id, Model model) {
	 pacientMedicationRepository.deleteMedicationByPatient(id);
	 visitScheduleRepository.deleteVisitsByPatient(id);
	 patientRepository.deleteById(id); 
	 return  "redirect:/getAllOutDoorPatient?delete=true"; }

	/*
	 * 
	 * 
	 * @RequestMapping(value = "/getAllPatient") public String getAllPatients1(Model
	 * model) { List<Patient> patients = patientRepository.findAll();
	 * model.addAttribute("patients", patients);
	 * 
	 * return "allpatient.html"; }
	 * 
	 * @RequestMapping(value = "deletePatientRec/{id}") public String
	 * deletePatientRec(@PathVariable String id, Model model) {
	 * //pacientMedicationRepository.deleteMedicationByPatient(id);
	 * visitScheduleRepository.deleteVisitsByPatient(id);
	 * patientRepository.deleteById(id); return
	 * "redirect:/getAllInDoorPatientRec?delete=true"; }
	 * 
	 * @RequestMapping(value = "deletePatientRecO/{id}") public String
	 * deletePatient(@PathVariable String id, Model model) {
	 * //pacientMedicationRepository.deleteMedicationByPatient(id);
	 * visitScheduleRepository.deleteVisitsByPatient(id);
	 * patientRepository.deleteById(id); return
	 * "redirect:/getAllOutDoorPatientRec?delete=true"; }
	 * 
	 * @RequestMapping(value = "/goToAddPatientForm") public String
	 * goToAddPatientForm(Model model) { model.addAttribute("patient", new
	 * Patient()); model.addAttribute("doctors",doctorRepository.findAll());
	 * model.addAttribute("genders", Gender.values()); model.addAttribute("rooms",
	 * roomRepository.findAll()); return "addPatient.html"; }
	 * 
	 * 
	 * 
	 * @PostMapping(value = "/addPatient") public String addPatient(@Valid Patient
	 * patient, BindingResult result,Model model) {
	 * if(patientRepository.findById(patient.getId()).isPresent()) { return
	 * "redirect:/goToAddPatientForm?error=true"; }
	 * patient.setDoctor(patient.getDoctor());
	 * patient.setGender(patient.getGender()); //
	 * patient.setRoom(patient.getRoom()); //patient.setId(patient.getPacient_id());
	 * //patient.setPacient_InOut(0); patientRepository.save(patient); return
	 * "redirect:/getAllInDoorPatient?success=true"; }
	 * 
	 * @PostMapping(value = "/editPatient/{id}") public String
	 * editPatient(@PathVariable String id,@Valid Patient patient, BindingResult
	 * result,Model model) { patient.setDoctor(patient.getDoctor());
	 * patient.setGender(patient.getGender());
	 * //patient.setPacient_room(patient.getPacient_room());
	 * //patient.setPacient_id(id); patientRepository.save(patient); return
	 * "redirect:/getAllInDoorPatient?edir=true"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 * 
	 * 
	 * 
	 * 
	 * 
	 * /*
	 * 
	 * 
	 * 
	 * @RequestMapping(value = "/getAllInDoorPatientRec") public String
	 * getAllPatientsRec(Model model) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); List<Patient>
	 * patients = patientRepository.getIndoorPatient();
	 * model.addAttribute("patientsI", patients);
	 * 
	 * return "inpatientrec.html"; }
	 * 
	 * @RequestMapping(value = "/getAllOutDoorPatientRec") public String
	 * getAllPatientsOutRec(Model model) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); List<Patient>
	 * patients = patientRepository.getOutdoorPatient();
	 * model.addAttribute("patients", patients);
	 * 
	 * return "outpatientrec.html"; }
	 * 
	 * @RequestMapping(value = "/goToAddOutPatientFormRec") public String
	 * goToAddOutPatientFormRec(Model model) { model.addAttribute("patient", new
	 * Patient()); model.addAttribute("doctors",doctorRepository.findAll());
	 * model.addAttribute("gender", genderRepository.findAll());
	 * model.addAttribute("rooms", roomRepository.findAll()); return
	 * "addPatientOutRec.html"; }
	 * 
	 * @RequestMapping(value = "/goToAddInPatientFormRec") public String
	 * goToAddInPatientFormRec(Model model) { model.addAttribute("patient", new
	 * Patient()); model.addAttribute("doctors",doctorRepository.findAll());
	 * model.addAttribute("gender", genderRepository.findAll());
	 * model.addAttribute("rooms", roomRepository.findAll()); return
	 * "addPatientInRec.html"; }
	 * 
	 * @PostMapping(value = "/addOutPatientRec") public String
	 * addOutPatientRec(@Valid Patient patient, BindingResult result,Model model) {
	 * if(patientRepository.findById(patient.getPacient_id()).isPresent()) { return
	 * "redirect:/goToAddOutPatientFormRec?error=true"; } Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * patient.setDoctor(patient.getDoctor());
	 * patient.setPacient_gender(patient.getPacient_gender());
	 * patient.setPacient_room(patient.getPacient_room());
	 * patient.setPacient_id(patient.getPacient_id()); patient.setPacient_InOut(1);
	 * patientRepository.save(patient); return
	 * "redirect:/getAllOutDoorPatientRec?success=true"; }
	 * 
	 * @PostMapping(value = "/addInPatientRec") public String addInPatientRec(@Valid
	 * Patient patient, BindingResult result,Model model) {
	 * if(patientRepository.findById(patient.getPacient_id()).isPresent()) { return
	 * "redirect:/goToAddInPatientFormRec?error=true"; } Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication();
	 * patient.setDoctor(patient.getDoctor());
	 * patient.setPacient_gender(patient.getPacient_gender());
	 * patient.setPacient_room(patient.getPacient_room());
	 * patient.setPacient_id(patient.getPacient_id()); patient.setPacient_InOut(1);
	 * patientRepository.save(patient); return
	 * "redirect:/getAllInDoorPatientRec?success=true"; }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
}
