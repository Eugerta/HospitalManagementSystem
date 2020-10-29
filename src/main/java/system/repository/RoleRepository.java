package system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system.model.Laborator;
import system.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	@Query(value="select * from role where name=?1 ",nativeQuery = true)

	Role findRoleByName(String name);

}
