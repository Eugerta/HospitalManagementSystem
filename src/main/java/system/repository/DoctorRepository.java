package system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import system.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String>{
	
	@Query(value = "select * from doctor where username =?1",nativeQuery = true)
     Optional<Doctor> findByUsername(String username);

}
