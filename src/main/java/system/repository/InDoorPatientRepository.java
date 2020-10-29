package system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.InDoorPatient;
import system.model.Patient;

public interface InDoorPatientRepository extends JpaRepository<InDoorPatient,String>{

	
	@Query(value = "select * from indoor_patient where doctor_id in (select id from doctor where username =?1)",nativeQuery = true)
	List<InDoorPatient> getInDoorPatientByDoctor(String doctor);
	
	
	@Query(value = "update indoor_patient set doctor_id=null where doctor_id=?1",nativeQuery = true)
    void setDoctorToNull(String doctor);
    
}
