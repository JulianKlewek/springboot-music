package pl.klewek.springbootmusic.service;

import pl.klewek.springbootmusic.exception.UserAlreadyExistsException;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.dto.UserDto;

public interface IUserService {

    void registerNewUser(UserDto userDto) throws UserAlreadyExistsException;

    void saveRegisteredUser(User user);

    User getRegisteredUser(UserDto userDto);

    void addBasicRoleToUser(User user);
}
