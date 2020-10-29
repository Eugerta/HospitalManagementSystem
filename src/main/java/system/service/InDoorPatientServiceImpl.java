package system.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.model.Doctor;
import system.model.InDoorPatient;
import system.model.InDoorVisitSchedule;
import system.model.PatientMedication;
import system.model.OutDoorVisitSchedule;
import system.repository.InDoorPatientRepository;
import system.repository.MedicationRepository;
import system.repository.PatientMedicationRepository;
import system.repository.RoomRepository;
import system.repository.InDoorVisitScheduleRepository;

@Service
public class InDoorPatientServiceImpl implements InDoorPatientService{

	@Autowired
	InDoorPatientRepository patientRepo;

	@Autowired
	RoomRepository roomRepo;

	@Autowired
	InDoorVisitScheduleRepository visitScheduleRepository;

	@Autowired
	PatientMedicationRepository patientMedRepository;
	
	@Autowired
	MedicationRepository medicationRepository;
	
	 private static final Logger logger = LogManager.getLogger(InDoorPatientServiceImpl.class);

	 @Override
	public InDoorPatient save(InDoorPatient inDoorPatient) {

		int availableBed = roomRepo.getAvailableBed(inDoorPatient.getDoctor().getDepartment().getId());
		if (availableBed > 0) {
			patientRepo.save(inDoorPatient);
		//	roomRepo.updateAvailableBed(inDoorPatient.getRoom().getRoom_number(), availableBed - 1);
		logger.info("A new indoor patient was added in room "+ inDoorPatient.getRoom().getRoom_number());
		}

		else {
			System.out.println("Not available bed");
			logger.error("Not available bed to add a new InDoor Patient");
		}
			

		return inDoorPatient;
	}

	public void addPatientVisit(InDoorVisitSchedule visitSchedule) {

		Date date = visitSchedule.getDate();
		Time time = visitSchedule.getTime();
		String doctorId = visitSchedule.getDoctor().getId();

		if (getFreeTimeVisitByDate(doctorId, date).contains(time)) {
			visitScheduleRepository.save(visitSchedule);
			logger.info("A new visit was added for doctor "+visitSchedule.getDoctor().getFirstName());
		}

		else {
			//System.out.println("this time is not available for date: " + date);
			logger.error("This time is not available for visit schedule: " + date);
		}
			
	}

	public List<InDoorVisitSchedule> getAllVisitByDoctor(String doctorId) {

		List<InDoorVisitSchedule> doctorVisits = visitScheduleRepository.findVisitsByDoctor(doctorId);

		return doctorVisits;
	}

	public List<Time> getFreeTimeVisitByDate(String doctorId, Date date) {
		List<Time> visitAvailableTimes = new ArrayList<Time>();
		visitAvailableTimes.add(Time.valueOf("08:00:00"));
		visitAvailableTimes.add(Time.valueOf("08:30:00"));
		visitAvailableTimes.add(Time.valueOf("09:00:00"));
		visitAvailableTimes.add(Time.valueOf("09:30:00"));
		visitAvailableTimes.add(Time.valueOf("10:00:00"));
		visitAvailableTimes.add(Time.valueOf("10:30:00"));
		visitAvailableTimes.add(Time.valueOf("11:00:00"));
		visitAvailableTimes.add(Time.valueOf("11:30:00"));
		visitAvailableTimes.add(Time.valueOf("12:00:00"));
		visitAvailableTimes.add(Time.valueOf("13:00:00"));
		visitAvailableTimes.add(Time.valueOf("13:30:00"));
		visitAvailableTimes.add(Time.valueOf("14:00:00"));
		visitAvailableTimes.add(Time.valueOf("14:30:00"));
		visitAvailableTimes.add(Time.valueOf("15:00:00"));
		visitAvailableTimes.add(Time.valueOf("15:30:00"));
		List<Time> busyTimes = visitScheduleRepository.getTimeVisitByDoctor(doctorId, date);

		List<Time> freeTimes = new ArrayList<Time>();
		for (Time t : visitAvailableTimes) {
			if (!busyTimes.contains(t)) {
				freeTimes.add(t);
			}
		}

		return freeTimes;
	}

	 @Override
	public void addPatientMedication(PatientMedication patientMed) {

		if (patientMed.getMedication().getQuantity() > patientMed.getQuantity()) {
			patientMed.setAvailableInDoor(true);
			patientMedRepository.save(patientMed);
			int quantityAvailable = medicationRepository.getQuantityById(patientMed.getMedication().getId());
			medicationRepository.updateMedicationQuantity(patientMed.getMedication().getId(),quantityAvailable-patientMed.getQuantity());
		
		} else {
			patientMed.setAvailableInDoor(false);
			patientMedRepository.save(patientMed);
			logger.warn("The medication "+patientMed.getMedication().getName()+" is not available in hospital anymore! ");
		}

	}
	
  @Override
	public String deleteInDoorPatientById(String id) {
		
		        patientMedRepository.deleteMedicationByPatient(id);
				visitScheduleRepository.deleteVisitsByPatient(id);
				patientRepo.deleteById(id);
				
				logger.info("Employee with id :"+ id+ "is deleted");
				
				return "SUCCESS";
	}

 @Override
public InDoorPatient editInDoorPatient(String id, InDoorPatient inDoorPatient) {
	
	  InDoorPatient oldInDoorPatient = patientRepo.findById(id).get();
	  inDoorPatient.setId(id);
	  patientRepo.save(inDoorPatient);
	  logger.info("Patient: "+ oldInDoorPatient.getFirstName()+" "+ oldInDoorPatient.getLastName()+" is edited" );

	return null;
}

}
