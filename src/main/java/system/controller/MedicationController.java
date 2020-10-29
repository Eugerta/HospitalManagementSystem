package system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Medication;
import system.repository.MedicationRepository;

@Controller
public class MedicationController {

	@Autowired
	private MedicationRepository medicationRepository;

	@RequestMapping(value = "/deleteMedication/{id}")
	private String deleteMedication(@PathVariable int id) {
		medicationRepository.deleteById(id);
		return "redirect:/getAllMedication?delete=true";
	}

	
	@GetMapping(value = "/getAllMedication")
	private String getAllMedication(Model model) {
		List<Medication> medications = medicationRepository.findAll();
		model.addAttribute("medications", medications);
		return "medications/medication.html";
	}

	@GetMapping(value = "/goToUpdateMedForm/{id}")
	public String goToUpdateMedForm(@PathVariable int id, Model model) {
		Medication medication = medicationRepository.findById(id).get();
		model.addAttribute("medication", medication);
		return "medications/editMedication.html";
	}

	@PostMapping(value = "/editMedication/{id}")
	public String editMedication(@PathVariable int id,@Valid Medication medication, BindingResult result, Model model) {
		System.out.println(medication.getId());
		medication.setId(id);
		medicationRepository.save(medication);
		return "redirect:/getAllMedication?edit=true";

	}

	@PostMapping(value = "/addMedication")
	private String addNewMedication(@Valid @ModelAttribute Medication medication, BindingResult result, Model model) {
		if(medicationRepository.findById(medication.getId()).isPresent()) {
			return "redirect:/goToAddMedForm?error=true";
		}
		medicationRepository.save(medication);
		return "redirect:/getAllMedication?success=true";
	}

	@RequestMapping(value = "/goToAddMedForm")
	private String goToAddMedForm(Model model) {
		model.addAttribute("medication", new Medication());
		return "medications/addMedication.html";
	}

}
