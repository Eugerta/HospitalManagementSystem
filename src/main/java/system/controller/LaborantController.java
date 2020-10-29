package system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Department;
import system.model.Gender;
import system.model.Laborant;
import system.repository.DepartmentRepository;
import system.service.DoctorService;
import system.service.LaborantService;
import system.repository.LaboratorRepository;

@Controller
public class LaborantController {

	@Autowired
	private LaborantService laborantService;
	@Autowired
	private LaboratorRepository LaboratorRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public LaborantController(LaborantService laborantService) {
		this.laborantService = laborantService;
	}
	
	@RequestMapping(value = "/getAllLaborant")
	public String getAllLaborant(Model model) {
		model.addAttribute("laborants",  laborantService.findAll());
		return "laborants/laborant.html";
	}
	
	@RequestMapping(value = "/deleteLaborant/{id}")
	public String deleteLaborant(@PathVariable String id) {
		laborantService.deleteById(id);
		return "redirect:/getAllLaborant?delete=true";
	}
	
	@RequestMapping(value = "/goToAddLaborantForm")
	public String goToAddLaborantForm(Model model) {
		model.addAttribute("laborant", new Laborant());
		model.addAttribute("genders",  Gender.values());
		model.addAttribute("departments",  departmentRepository.findAll());
		model.addAttribute("laborators", LaboratorRepository.findAll());
		return "laborants/addLaborant.html";
	}
	
	@RequestMapping(value = "/goToEditLaborantForm/{id}")
	public String goToEditLaborantForm(@PathVariable String id,Model model) {
		model.addAttribute("laborant",laborantService.findById(id));
		model.addAttribute("departments",  departmentRepository.findAll());
		model.addAttribute("genders",  Gender.values());
		model.addAttribute("laborators", LaboratorRepository.findAll());
		return "laborants/editLaborant.html";
	}
	
	@PostMapping(value = "/addLaborant")
	public String addLaborant(@Valid Laborant laborant,BindingResult result, Model model) {
		laborant.setLaborator(laborant.getLaborator());
		laborantService.save(laborant);
		return "redirect:/getAllLaborant?success=true";
	}
	
	@PostMapping(value = "/editLaborant/{id}")
	public String editLaborant(@PathVariable String id,@Valid Laborant laborant,BindingResult result, Model model) {
		
		laborant.setId(id);
		laborantService.save(laborant);
		return "redirect:/getAllLaborant?edit=true";
	}
}
