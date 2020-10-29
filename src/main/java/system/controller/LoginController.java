package system.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {


	@RequestMapping( value = "/login", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	@PostMapping("/logout")
	 public String destroySession(HttpServletRequest request) {
	  request.getSession().invalidate();
	  return "redirect:/";
	 }



}