package pl.klewek.springbootmusic.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.klewek.springbootmusic.model.UserRole;
import pl.klewek.springbootmusic.repository.UserRepository;
import pl.klewek.springbootmusic.repository.UserRoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final static Logger LOGGER = LogManager.getLogger(MyUserDetailsService.class);

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        if(!userRepository.existsUserByEmail(email)){
            LOGGER.warn("Login failed. Cannot find username: '{}' in database", email);
            throw new UsernameNotFoundException("Username doesn't exists " + email);
        }

        pl.klewek.springbootmusic.model.User user = userRepository.findByEmail(email);

        UserDetails userDetails = new User(user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));

        return userDetails;
    }

    private static List<GrantedAuthority> getAuthorities (Set<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }
}
