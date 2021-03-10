package pl.klewek.springbootmusic.service;

import pl.klewek.springbootmusic.model.Role;

public interface IRoleService {

    void saveRole(Role role);

    Role getRoleAdmin();

    Role getRoleUser();

}
