package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
