package system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system.model.InDoorPatient;
import system.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,String>{

	
	//@Query(value="select * from patient where pacient_in_out = 0",nativeQuery = true)
	//List<Patient> getIndoorPatient();
	
	@Query(value = "select * from patient where doctor_id =?1",nativeQuery = true)
	List<Patient> getPatientByDoctor(String doctor);
	
	
	@Query(value = "update patient set doctor_id=null where doctor_id=?1",nativeQuery = true)
    void setDoctorToNull(String doctor);
	
	@Query(value = "select * from patient where doctor_id in (select id from doctor where username =?1)",nativeQuery = true)
	List<Patient> getOutDoorPatientByDoctor(String doctor);
	
    
}
