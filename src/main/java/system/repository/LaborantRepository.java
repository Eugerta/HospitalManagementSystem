package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.model.Laborant;

public interface LaborantRepository extends JpaRepository<Laborant, String>{

}
