package system.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import system.model.Doctor;
import system.service.DoctorService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


public class DoctorControllerTest {
	
	    @Mock
	    DoctorService doctorService;

	    @Mock
	    Model model;

	    DoctorController controller;

	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        controller = new DoctorController(doctorService);
	    }

	    @Test
	    public void getAllDoctorTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        mockMvc.perform(get("/getAllDoctors"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("doctors/doctors.html"));
	    }
	    
	    @Test
	    public void deleteDoctorTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        mockMvc.perform(get("/getAllDoctors"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("doctors/doctors.html"));
	        
	       when(doctorService.deleteDoctor("1")).thenReturn("SUCCESS");
	        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteDoctor/1"))
	                .andExpect(view().name("redirect:/getAllDoctors"));
	    }
	    
	    @Test
	    public void addDoctorTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        Doctor doc = new Doctor();
	        when(doctorService.addDoctor(doc)).thenReturn(doc);
	        mockMvc.perform(MockMvcRequestBuilders.post("/addDoctor", doc))
	                .andExpect(view().name("redirect:/getAllDoctors?success=true"));
	    }

	    @Test
	    public void editDoctorTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        Doctor doc = new Doctor();
	        doc.setFirstName("Lori");
	        when(doctorService.editDoctor("1",doc)).thenReturn(doc);
	        mockMvc.perform(MockMvcRequestBuilders.post("/editDoctor/1", "1", doc))
	                .andExpect(view().name("redirect:/getAllDoctors?edit=true"));
	 
	        assertEquals(doc.getFirstName(), "Lori");
	    }

}
