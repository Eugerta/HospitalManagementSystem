package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.model.Test;

public interface TestRepository extends JpaRepository<Test, Integer>{

}
