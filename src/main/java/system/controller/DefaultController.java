package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.User;
import system.repository.UserRepository;

@Controller
public class DefaultController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/defaultURL")
	public String showDefaultView() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findUserByUsername(auth.getName());
		String userRole = u.getRole().getName();

		if (userRole.equals("Admin")) {
			return "redirect:/Admin/adminPage.html";

		}
		if (userRole.equals("Recepsionist")) {
			return "redirect:/recepsionistPage.html";

		} else {

			return "redirect:/doctors/doctorPage.html";
		}

	}

}
