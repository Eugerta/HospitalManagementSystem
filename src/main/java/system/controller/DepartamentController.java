package system.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import system.model.Department;
import system.repository.DepartmentRepository;

@Controller
public class DepartamentController {
	
	@Autowired
	private DepartmentRepository departamentRepository;
	
	@RequestMapping(value ="/deleteDepartament/{id}")
	public String deleteDepartament(@PathVariable int id){
		departamentRepository.deleteById(id);
		return "redirect:/getAllDepartaments";
	}
	
	@GetMapping(value = "/getAllDepartaments")
	public String getAllDepartaments(Model model){
		
		List<Department> departaments = departamentRepository.findAll();
		model.addAttribute("departaments",departaments);
	
		return "departament.html" ;
	}
	
	//@RequestMapping(value = { "/addDepartaments" }, method = RequestMethod.POST)
    @PostMapping("/addDepartaments")
    public String addDepartament(@Valid @ModelAttribute Department departament,BindingResult result, Model model) {
    	System.out.println("dep");
        departamentRepository.save(departament);
           return "redirect:/getAllDepartaments?success=true";
       
    }
	
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
    //@PostMapping("/addDepartaments")
    public String addDepartament1(Model model) {
    	System.out.println("dep11");
    	  model.addAttribute("departament", new Department());
   		return "addD.html";
       
    }

}
