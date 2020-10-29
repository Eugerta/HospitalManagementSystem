package system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.PatientMedication;

public interface PatientMedicationRepository extends JpaRepository<PatientMedication,Integer>{
	
	@Query(value = "select * from patient_medication where patient_id=?1",nativeQuery = true)
	List<PatientMedication> getPatientMedication(String patientId);
	
	@Query(value = "delete from patient_medication where patient_id=?1",nativeQuery = true)
	void deleteMedicationByPatient(String patientId);

}
