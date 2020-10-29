package system.service;

import java.util.List;
import java.util.Set;

import system.model.InDoorVisitSchedule;
import system.model.OutDoorVisitSchedule;

public interface VisitService {
	
	Set<InDoorVisitSchedule> getAllRemainingVisits(String doctorId);
	
    List<InDoorVisitSchedule> getAllCommingVisits(String doctorId);
    
    List<InDoorVisitSchedule> findAll();
    
    InDoorVisitSchedule findById(int visitId);
    
    void deleteById(int visitId);
    
    InDoorVisitSchedule save(InDoorVisitSchedule inDoorVisitSchedule);
    
    List<InDoorVisitSchedule> findVisitsByDoctor(String doctor);
    
    List<InDoorVisitSchedule> todayVisitsByDoc( String doctorId);
    
    

}
