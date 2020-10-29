package system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Laborator;
import system.repository.LaboratorRepository;

@Controller
public class LaboratorController {

	@Autowired
	private LaboratorRepository laboratorRepository;
	
	@RequestMapping(value = "/getAllLaborator")
	public String getAllLaborator(Model model) {
		model.addAttribute("laborators", laboratorRepository.findAll());
		return "laborator.html";
	}
	
	@RequestMapping(value = "/deleteLaborator/{id}")
	public String deleteLaborator(@PathVariable int id){
		laboratorRepository.deleteById(id);
		return "redirect:/getAllLaborator?delete=true";
	}
	@RequestMapping(value = "/goToAddLaboratorForm")
	public String goToAddLaboratorForm(Model model) {
		model.addAttribute("laborator", new Laborator());
		return "addLaborator.html";
	}
	
	@RequestMapping(value = "/goToEditLaboratorForm{id}")
	public String goToEditLaboratorForm(@PathVariable int id,Model model) {
		model.addAttribute("laborator", laboratorRepository.findById(id));
		return "editLaborator.html";
	}
	
	@PostMapping(value = "/addLaborator")
	public String addLaborator(@Valid Laborator laborator, BindingResult result, Model model) {
		laborator.setId(laborator.getId());
		laboratorRepository.save(laborator);
		return "redirect:/getAllLaborator?success=true";
	}
	
	@PostMapping(value = "/editLaborator/{id}")
	public String editLaborator(@PathVariable int id,@Valid Laborator laborator, BindingResult result, Model model) {
		laborator.setId(id);
		laboratorRepository.save(laborator);
		return "redirect:/getAllLaborator?edit=true";
	}
}
