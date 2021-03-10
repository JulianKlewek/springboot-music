package pl.klewek.springbootmusic.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.klewek.springbootmusic.exception.UserAlreadyExistsException;
import pl.klewek.springbootmusic.exception.UserDoesNotExistsException;
import pl.klewek.springbootmusic.model.Role;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.UserRole;
import pl.klewek.springbootmusic.model.dto.UserDto;
import pl.klewek.springbootmusic.repository.UserRepository;
import pl.klewek.springbootmusic.repository.UserRoleRepository;
import pl.klewek.springbootmusic.repository.VerificationTokenRepository;

@Service
public class UserService implements IUserService{

    private final static Logger LOGGER = LogManager.getLogger(UserService.class);

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepository tokenRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       VerificationTokenRepository tokenRepository, RoleService roleService,
                       UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.roleService = roleService;
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void registerNewUser(final UserDto userDto) throws UserAlreadyExistsException {
        if (userRepository.existsUserByEmail(userDto.getEmail())){
            LOGGER.warn("Email already exists in base {}", userDto.getEmail());
            throw new UserAlreadyExistsException("User with given email already exists in base");
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        LOGGER.info("New user registered: {}", user.getEmail());
        saveRegisteredUser(user);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void addBasicRoleToUser(User user){
        Role role = roleService.getRoleUser();
        UserRole userRole  = new UserRole(user,role);
        userRoleRepository.save(userRole);
    }

    @Override
    public User getRegisteredUser(UserDto userDto) throws UserDoesNotExistsException{
        if (!userRepository.existsUserByEmail(userDto.getEmail())){
            throw new UserDoesNotExistsException("User with given email does not exists in base");
        }
        return userRepository.findByEmail(userDto.getEmail());
    }
}
