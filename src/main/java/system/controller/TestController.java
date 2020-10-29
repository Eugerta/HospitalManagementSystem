package system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Test;
import system.repository.LaborantRepository;
import system.repository.LaboratorRepository;
import system.repository.TestRepository;

@Controller
public class TestController {
	@Autowired	
	private TestRepository testRepository;
	@Autowired
	private LaborantRepository laborantRepository;
	
	@RequestMapping(value = "/getAllTest")
	public String getAllTest(Model model) {
		model.addAttribute("tests", testRepository.findAll());
		return "tests/test.html";
	}
	
	@RequestMapping(value = "/deleteTest/{id}")
	public String deleteTest(@PathVariable int id) {
		testRepository.deleteById(id);
		return "redirect:/getAllTest";
	}
	
	@RequestMapping(value = "/goToAddTestForm")
	public String goToAddTestForm(Model model) {
		model.addAttribute("test", new Test());
		model.addAttribute("laborants", laborantRepository.findAll());
		return "tests/addTest.html";
	}
	@PostMapping(value = "/addTest")
	public String goToAddTestForm(@Valid Test test, BindingResult result,Model model) {
		
		testRepository.save(test);
		return "redirect:/getAllTest";
	}

}
