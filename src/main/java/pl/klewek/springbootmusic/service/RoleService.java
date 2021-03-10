package pl.klewek.springbootmusic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klewek.springbootmusic.model.Role;
import pl.klewek.springbootmusic.repository.RoleRepository;

@Service
public class RoleService implements IRoleService{

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role getRoleAdmin() {
        return roleRepository.findByRoleName("ROLE_ADMIN");
    }

    @Override
    public Role getRoleUser() {
        return roleRepository.findByRoleName("ROLE_USER");
    }
}
