package system.services;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import system.model.InDoorVisitSchedule;
import system.model.OutDoorVisitSchedule;
import system.repository.InDoorVisitScheduleRepository;
import system.service.VisitServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class VisitServiceImplTest {
	
	@Mock
	VisitServiceImpl visitService;

    @Mock
    InDoorVisitScheduleRepository visitScheduleRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        visitService = new VisitServiceImpl(visitScheduleRepository);
    }

    @Test
    public void getAllRemainingVisits() throws Exception {

    	InDoorVisitSchedule visit = new InDoorVisitSchedule();
        List<InDoorVisitSchedule> visitData = new ArrayList<InDoorVisitSchedule>();
        visitData.add(visit);

       when(visitScheduleRepository.findCommingVisitsByDoctor("B12EH")).thenReturn(visitData);
        
      List<InDoorVisitSchedule> visitList = visitService.getAllCommingVisits("B12EH");
      assertEquals(visitList.size(), 1);
      verify(visitScheduleRepository, times(1)).findCommingVisitsByDoctor("B12EH");
    
    }
    
  @Test
  public void deleteByIdTest() {
    	visitService.deleteById(1);

        verify(visitScheduleRepository).deleteById(1);
    }
  
  @Test
  public void findByDoctorTest() {
    	visitService.findVisitsByDoctor("1");

        verify(visitScheduleRepository).findVisitsByDoctor("1");
    }

}
