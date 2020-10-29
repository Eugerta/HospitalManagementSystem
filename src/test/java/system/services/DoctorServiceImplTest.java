package system.services;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import system.model.Doctor;
import system.repository.DoctorRepository;
import system.repository.UserRepository;
import system.service.DoctorService;
import system.service.DoctorServiceImpl;



public class DoctorServiceImplTest {
	
	@Mock
	DoctorRepository docRepo;
	
	@Mock
	UserRepository userRepo;
	
	private DoctorService doctorService;
	
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doctorService = new DoctorServiceImpl(docRepo, userRepo);
    }
   
    
    @Test
    public void getDoctor() throws Exception {
    	
    	 List<Doctor> doctors = new ArrayList<Doctor>() ; 
    	 doctors.add(new Doctor());
    	 
    	 when(docRepo.findAll()).thenReturn(doctors);
    	 
    	List<Doctor> doctorList = doctorService.getAllDoctor();
    	     assertEquals(doctorList.size(), 1);
    	    verify(docRepo, times(1)).findAll();
    	
    }
    
    
    @Test
    public void editDoctorTest() throws Exception {
    	
    	 Doctor doc = new Doctor();
	     doc.setFirstName("Lori");
	     
	     Optional<Doctor> doctorOp = Optional.of(doc);
	     when(docRepo.findById("1")).thenReturn(doctorOp);
	     Doctor editedDoctor = doctorService.editDoctor("1",doc);
    	 
	     assertEquals(editedDoctor.getFirstName(), doc.getFirstName());
	     verify(docRepo, times(1)).findById("1");
    	
    }
    
    @Test
    public void deleteDoctorTest() throws Exception {
    	      
	    String result = doctorService.deleteDoctor("1");
	    
	    assertEquals(result, "SUCCESS");
       verify(docRepo, times(1)).deleteById("1");
    	
    }

}
