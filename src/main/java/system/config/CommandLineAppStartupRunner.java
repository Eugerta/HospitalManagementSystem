package system.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import system.repository.RoleRepository;
import system.repository.UserRepository;
import system.model.Role;
import system.model.User;

@Component    
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String...args) throws Exception {
    	
        User admin = new User();
        admin.setUsername("username");
        admin.setPassword(bCryptPasswordEncoder.encode("password"));
        admin.setActive(true);
        Role role = new Role();
        role.setName("Admin");
        role.setId(4);
        roleRepository.save(role);
        admin.setId("1234");
        admin.setRole(role);
        userRepository.save(admin);
    }
}