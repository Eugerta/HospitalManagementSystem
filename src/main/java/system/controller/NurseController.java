package system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Gender;
import system.model.Nurse;
import system.repository.DepartmentRepository;
import system.repository.NurseRepository;

@Controller
public class NurseController {

	@Autowired
	private NurseRepository nurseRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@RequestMapping(value = "/getAllNurse")
	public String getAllNurses(Model model) {
		model.addAttribute("nurses", nurseRepository.findAll());
		return "nurses/nurse.html";
	}

	@RequestMapping(value = "/deleteNurse/{id}")
	public String deleteNurse(@PathVariable String id) {
		nurseRepository.deleteById(id);
		return "redirect:/getAllNurse?delete=true";
	}

	@RequestMapping(value = "/goToAddNurseForm")
	public String goToAddNurseForm(Model model) {
		model.addAttribute("nurse", new Nurse());
		model.addAttribute("genders",  Gender.values());
		model.addAttribute("departments", departmentRepository.findAll());
		return "nurses/addNurse.html";
	}

	@RequestMapping(value = "/goToEditNurseForm/{id}")
	public String goToEditNurseForm(@PathVariable String id, Model model) {
		model.addAttribute("nurse", nurseRepository.findById(id).get());
     	model.addAttribute("genders",  Gender.values());
		model.addAttribute("departments", departmentRepository.findAll());
		return "nurses/editNurse.html";
	}

	@PostMapping(value = "/addNurse")
	public String addNurse(@Valid Nurse nurse, BindingResult result, Model model) {
		
		nurse.setId(nurse.getId());
		nurseRepository.save(nurse);
		return "redirect:/getAllNurse?success=true";
	}

	@PostMapping(value = "/editNurse/{id}")
	public String editNurse(@PathVariable String id, @Valid Nurse nurse, BindingResult result, Model model) {
	
        nurse.setId(id);
		nurseRepository.save(nurse);
		return "redirect:/getAllNurse?edit=true";
	}
}