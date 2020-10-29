package system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	@Query(value = "select * from user where username=?1",nativeQuery = true)
	User findUserByUsername(String name);
	
	//User findUserByUsername

}
