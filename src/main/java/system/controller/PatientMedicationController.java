package system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.PatientMedication;
import system.repository.InDoorPatientRepository;
import system.repository.MedicationRepository;
import system.repository.PatientMedicationRepository;
import system.repository.PatientRepository;

@Controller
public class PatientMedicationController {

	@Autowired
	public PatientMedicationRepository patientMedicationRepository;
	@Autowired
	public InDoorPatientRepository inPatientRepository;
	@Autowired
	public MedicationRepository medicationRepository;

	@RequestMapping(value = "/getPatientMedication/{id}")
	public String getPatientMedication(@PathVariable String id, Model model) {
		model.addAttribute("patientMedication", patientMedicationRepository.getPatientMedication(id));

		return "patients/patientMedication.html";
	}

	@RequestMapping(value = "/deletePatientMedication/{id}")
	public String deletePatientMedication(@PathVariable int id) {
		patientMedicationRepository.deleteById(id);
		return "redirect:/getPatientMedication?delete=true";
	}

	@RequestMapping(value = "/deleteMedicationByPatient/{id}")
	private void deleteMedicationByPatient(@PathVariable String patient) {
		patientMedicationRepository.deleteMedicationByPatient(patient);
	}

	@RequestMapping(value = "/goToAddMedicationToPatientForm/{id}")
	public String goToAddMedicationToPatientForm(@PathVariable String id, Model model) {
        PatientMedication p = new PatientMedication();
		p.setInDoorPatient(inPatientRepository.findById(id).get());
        model.addAttribute("patientMedication", p);
		model.addAttribute("medications", medicationRepository.findAll());
		
		return "patients/addPatientMedication.html";
	}

	@PostMapping(value = "/addMedicationToPatient")
	public String addMedicationToPatient(@Valid PatientMedication patientMedication, BindingResult ressult,
			Model model) {
		patientMedicationRepository.save(patientMedication);
		String patientId = patientMedication.getInDoorPatient().getId();
		return "redirect:/getPatientMedication/" + patientId + "?success=true";

	}

	@RequestMapping(value = "/goToEditMedicationToPatientForm/{id}")
	public String goToEditdMedicationToPatientForm(@PathVariable int id, Model model) {

		model.addAttribute("patientMedication", patientMedicationRepository.findById(id).get());
		model.addAttribute("medications", medicationRepository.findAll());
		return "patients/editPatientMedication.html";
	}

	@PostMapping(value = "/editMedicationToPatient/{id}")
	public String editMedicationToPatient(@PathVariable int id, @Valid PatientMedication pacientMedication,
			BindingResult ressult, Model model) {
		pacientMedication.setMedication(pacientMedication.getMedication());
		pacientMedication.setId(id);
		patientMedicationRepository.save(pacientMedication);
		 String patientId = pacientMedication.getInDoorPatient().getId();
		return "redirect:/getPatientMedication/"+patientId+"?edit=true";
	}
}
