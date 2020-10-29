package system.service;

import system.model.InDoorPatient;
import system.model.PatientMedication;

public interface InDoorPatientService {
	
	public InDoorPatient save(InDoorPatient inDoorPatient);
	
    public void addPatientMedication(PatientMedication patientMed) ;
	
	public InDoorPatient editInDoorPatient(String id,InDoorPatient inDoorPatient);
	
	public String deleteInDoorPatientById(String id);
	
	

}
