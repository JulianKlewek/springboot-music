package pl.klewek.springbootmusic.service;

import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.VerificationToken;

public interface IVerifTokenService {

    void createVerificationToken(User user, String token);

    boolean isTokenExists(String token);

    VerificationToken getVerificationToken(String token);

    VerificationToken getVerificationToken(User user);

}
