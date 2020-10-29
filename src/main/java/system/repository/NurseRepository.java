package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.model.Nurse;

public interface NurseRepository extends JpaRepository<Nurse, String>{

}
