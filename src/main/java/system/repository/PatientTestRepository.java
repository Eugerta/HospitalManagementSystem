package system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.PatientTest;

public interface PatientTestRepository extends JpaRepository<PatientTest, Integer>{

	
	@Query(value = "select * from patient_test where patient_id =?1",nativeQuery = true)
	List<PatientTest> getTestByPatient(String id);
	
	@Query(value = "select * from patient_test where laborant_id=?1",nativeQuery = true)
	List<PatientTest> getTestByLaborant(String id);
	
	@Query(value = "select * from patient_test where laboratort_id=?1",nativeQuery = true)
	List<PatientTest> getTestByLaborator(int id);
	
}
