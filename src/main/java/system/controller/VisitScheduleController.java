package system.controller;

import java.sql.Date;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import system.model.InDoorVisitSchedule;
import system.model.OutDoorVisitSchedule;
import system.repository.DoctorRepository;
import system.repository.InDoorPatientRepository;
import system.repository.PatientRepository;
import system.service.VisitService;
import system.repository.InDoorVisitScheduleRepository;

@Controller
public class VisitScheduleController {
	

	@Autowired
	public InDoorPatientRepository pacientRepository;
	@Autowired
	public DoctorRepository doctorRepository;
	
	@Autowired
	public VisitService visitScheduleService;
	
	@RequestMapping(value = "/getAllAppointments")
	public String getAllVists(Model model) {
		model.addAttribute("visits", visitScheduleService.findAll());
		return "visits/visit.html";
	}
	
	@RequestMapping(value = "/getAllAppointmentsRec")
	public String getAllVistsRec(Model model) {
		model.addAttribute("visits", visitScheduleService.findAll());
		return "visits/visitRec.html";
	}
	
	@RequestMapping(value = "/getAllVisitsBydoctor/{id}")
	public String getAllVistsByDoctor(@PathVariable String id,Model model) {
		model.addAttribute("visitsByDoctor", visitScheduleService.findVisitsByDoctor(id));
		return "visits/visitdoctor.html";
	}
	
	@RequestMapping(value = "/getAllVisitsBydoctorlog")
	public String getAllVistsByDoctorlog(String id,Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String docId = doctorRepository.findByUsername(auth.getName()).get().getId();
		model.addAttribute("visits", visitScheduleService.findVisitsByDoctor(docId));
		return "visits/visitdoctorlog.html";
	}
	@RequestMapping(value = "/getTodayVisitByDoctor/{id}")
	public String getTodayVisitByDoctor(@PathVariable String id,Model model) {
		model.addAttribute("visitsByDoctor", visitScheduleService.todayVisitsByDoc(id));
		return "visits/visit.html";
	}
	
	@RequestMapping(value = "/getTodayVisitDoc")
	public String getTodayVisit(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String docId = doctorRepository.findByUsername(auth.getName()).get().getId();
		model.addAttribute("visits", visitScheduleService.todayVisitsByDoc( docId));
		return "visits/visitDoctorToday.html";
	}
	
	
   @RequestMapping(value = "/deleteVisit/{id}")
   public String deleteVisit(@PathVariable int id) {
	   visitScheduleService.deleteById(id);
	   return "/getAllAppointments?delete=true";
   }
   
   @RequestMapping(value = "/goToAddVisitForm")
   public String goToAddVistitForm(Model model) {
	   model.addAttribute("visit", new OutDoorVisitSchedule());
	   model.addAttribute("doctors", doctorRepository.findAll());
	   model.addAttribute("patients", pacientRepository.findAll());
	   return "addVisit.html";
   }
   
   @RequestMapping(value = "/goToeditVisitForm/{id}")
   public String goToEditVistitForm(@PathVariable int id, Model model) {
	   model.addAttribute("visit", visitScheduleService.findById(id));
	   model.addAttribute("doctors", doctorRepository.findAll());
	   model.addAttribute("patients", pacientRepository.findAll());
	   return "editVisit.html";
   }
   
   @PostMapping(value = "/addVisit")
   public String addVisit(@Valid InDoorVisitSchedule visit, BindingResult result, Model model) {
	   
	   visitScheduleService.save(visit);
	   return "redirect:/getAllAppointments?success=true";
   }
   
   @PostMapping(value = "/editVisit/{id}")
   public String editVisit(@PathVariable int id,@Valid InDoorVisitSchedule visit, BindingResult result, Model model) {
	   
	   visit.setVisitId(id);
	   visitScheduleService.save(visit);
	   return "redirect:/getAllVisits?edit=true";
   }
   
   
   @RequestMapping(value = "/goToAddVisitFormDoc")
   public String goToAddVistitFormDoc(Model model) {
	   model.addAttribute("visit", new OutDoorVisitSchedule());
	   model.addAttribute("patients", pacientRepository.findAll());
	   return "visits/addVisitDoc.html";
   }
   
   @PostMapping(value = "/addVisitDoc")
   public String addVisitDoc(@Valid InDoorVisitSchedule visit, BindingResult result, Model model) {
	   Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	   visit.setDoctor(doctorRepository.findByUsername(auth.getName()).get());
	   visit.setVisitId(visit.getVisitId());
	   visitScheduleService.save(visit);
	   return "redirect:/getAllVisitsBydoctorlog?success=true";
   }
   
}
