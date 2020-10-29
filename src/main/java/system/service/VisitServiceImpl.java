package system.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.InDoorVisitSchedule;
import system.model.OutDoorVisitSchedule;
import system.repository.InDoorVisitScheduleRepository;

@Service
public class VisitServiceImpl implements VisitService{
	
	//@Autowired
private  InDoorVisitScheduleRepository visitScheduleRepository;

public	VisitServiceImpl(InDoorVisitScheduleRepository visitScheduleRepository) {
	this.visitScheduleRepository = visitScheduleRepository;
}

	@Override
	public Set<InDoorVisitSchedule> getAllRemainingVisits(String doctorId) {
		
		Set<InDoorVisitSchedule> visitRemaining = new HashSet<InDoorVisitSchedule>();
		visitScheduleRepository.findCommingVisitsByDoctor(doctorId).iterator().forEachRemaining(visitRemaining::add);
		
		return visitRemaining;
	}
	
	
	@Override
	public List<InDoorVisitSchedule> getAllCommingVisits(String doctorId) {
		
		List<InDoorVisitSchedule> visitRemaining = visitScheduleRepository.findCommingVisitsByDoctor(doctorId);
		
		return visitRemaining;
	}
	

	@Override
	public List<InDoorVisitSchedule> findAll() {
		List<InDoorVisitSchedule> list =  visitScheduleRepository.findAll();
		return list;
	}

	@Override
	public InDoorVisitSchedule findById(int visitId) {
		
		return visitScheduleRepository.findById(visitId).get();
	}

	@Override
	public void deleteById(int visitId) {
		
		visitScheduleRepository.deleteById(visitId);
	}

	@Override
	public InDoorVisitSchedule save(InDoorVisitSchedule inDoorVisitSchedule) {
		
		return visitScheduleRepository.save(inDoorVisitSchedule);
	}

	@Override
	public List<InDoorVisitSchedule> findVisitsByDoctor(String doctor) {
		
		return visitScheduleRepository.findVisitsByDoctor(doctor);
	}

	@Override
	public List<InDoorVisitSchedule> todayVisitsByDoc(String doctorId) {
		
		return visitScheduleRepository.todayVisitsByDoc(doctorId);
	}
	
	
	
	
	
	

}
