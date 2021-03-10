package pl.klewek.springbootmusic.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import pl.klewek.springbootmusic.model.Role;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.UserRole;
import pl.klewek.springbootmusic.repository.RoleRepository;
import pl.klewek.springbootmusic.repository.UserRepository;
import pl.klewek.springbootmusic.repository.UserRoleRepository;

@Configuration
public class DataBaseFiller {

    private final static Logger LOGGER = LogManager.getLogger(DataBaseFiller.class);

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public DataBaseFiller(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    private void fillDataBase(){
        addBasicRoles();
        LOGGER.info("Basic roles added to base");
        addFirstUsers();
        LOGGER.info("First users added");
        assignRolesToFirstUsers();
        LOGGER.info("Roles assigned to first users");
    }

    private void addBasicRoles(){
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleRepository.save(role1);
        roleRepository.save(role2);
    }

    private void addFirstUsers(){
        User user1 = new User("jan","kowlaski",passwordEncoder().encode("User1234%"),"jankowalski@mail.com",true);
        User user2 = new User("julian","klewek",passwordEncoder().encode("User1234%"),"julianklewek@gmail.com",true);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    private void assignRolesToFirstUsers(){
        User user1 = userRepository.findByEmail("julianklewek@gmail.com");
        Role role1 = roleRepository.findByRoleName("ROLE_ADMIN");
        User user2 = userRepository.findByEmail("jankowalski@mail.com");
        Role role2 = roleRepository.findByRoleName("ROLE_USER");
        UserRole userRole1 = new UserRole(user1,role1);
        UserRole userRole2 = new UserRole(user2,role2);
        userRoleRepository.save(userRole1);
        userRoleRepository.save(userRole2);
    }
}
