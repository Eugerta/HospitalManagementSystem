package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import system.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,String>{

	public Admin findByfirstName(String firstName);
}
