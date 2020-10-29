package system.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import system.model.Laborant;
import system.service.LaborantService;
import system.service.LaborantService;

public class LaborantControllerTest {
	
	    @Mock
	    LaborantService laborantService;

	    @Mock
	    Model model;

	    
	    LaborantController controller;

	    @Before
	    public void setUp() throws Exception {
	        MockitoAnnotations.initMocks(this);
	        controller = new LaborantController(laborantService);
	       
	    }

	    @Test
	    public void getLaborantTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        mockMvc.perform(get("/getAllLaborant"))
	                .andExpect(status().isOk())
	                .andExpect(view().name("laborants/laborant.html"));
	    }
	    
	    @Test
	    public void deleteLaborantTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	       when(laborantService.deleteById("1")).thenReturn("SUCCESS");
	        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteLaborant/1"))
	                .andExpect(view().name("redirect:/getAllLaborant?delete=true"));
	    }
	    
	    @Test
	    public void addLaborantTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        Laborant laborant = new Laborant();
	        when(laborantService.save(laborant)).thenReturn(laborant);
	        mockMvc.perform(MockMvcRequestBuilders.post("/addLaborant", laborant))
	                .andExpect(view().name("redirect:/getAllLaborant?success=true"));
	    }

	    @Test
	    public void editLaborantTest() throws Exception {
	        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	        Laborant laborant = new Laborant();
	        laborant.setFirstName("Lori");
	        when(laborantService.save(laborant)).thenReturn(laborant);
	        mockMvc.perform(MockMvcRequestBuilders.post("/editLaborant/1", "1", laborant))
	                .andExpect(view().name("redirect:/getAllLaborant?edit=true"));
	 
	        assertEquals(laborant.getFirstName(), "Lori");
	    }


}
