package system.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.Doctor;
import system.model.Recepsionist;
import system.model.Role;
import system.model.User;
import system.repository.RecepsionistRepository;
import system.repository.UserRepository;

@Controller
public class RecepsionitController {

	@Autowired
	private RecepsionistRepository recepsiontRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
	private UserRepository userRepository;
	@RequestMapping(value = "/getAllRecepsionist")
	public String getAllRecepsionit(Model model) {
		model.addAttribute("recepsionist", recepsiontRepository.findAll());
		return "recepsionist.html";
	}
	
	@RequestMapping(value = "/deleteRecepsionist/{id}")
	public String deleteRecepsionist(@PathVariable String id) {
		recepsiontRepository.deleteById(id);
		userRepository.deleteById(id);
		return "redirect:/getAllRecepsionist?delete=true";
	}
	
	@RequestMapping(value = "/goToAddRecepsionistForm")
	public String goToAddRecepsionistForm(Model model) {
		model.addAttribute("recepsionist", new Recepsionist());
		return "addRecepsionist.html";
	}
	
	@RequestMapping(value = "/goToEditRecepsionistForm/{id}")
	public String goToEditRecepsionistForm(@PathVariable String id,Model model) {
		model.addAttribute("recepsionist", recepsiontRepository.findById(id));
		return "addRecepsionist.html";
	}
	@PostMapping(value = "/addRecepsionist")
	public String addRecepsionist(@Valid Recepsionist rec, BindingResult result, Model model) {
		if(recepsiontRepository.findById(rec.getId()).isPresent()) {
			return "redirect:/goToAddRecepsionistForm?error=true";
		}
		rec.setId(rec.getId());
		Doctor doc = new Doctor();
		//doc.setDoctor_id(rec.getId());
		rec.setPassword(bCryptPasswordEncoder.encode(rec.getPassword()));
		recepsiontRepository.save(rec);
		User u = new User();
		u.setId(rec.getId());
		u.setPassword(bCryptPasswordEncoder.encode(rec.getPassword()));
		Role role = new Role();
		role.setId(3);
		u.setRole(role);
		userRepository.save(u);
		return "redirect:/getAllRecepsionist?success=true";
	}
	
	@PostMapping(value = "/editRecepsionist/{id}")
	public String editRecepsionist(@PathVariable String id,@Valid Recepsionist rec, BindingResult result, Model model) {
		rec.setId(id);
		recepsiontRepository.save(rec);
		return "redirect:/getAllRecepsionist?edit=true";
	}
}
