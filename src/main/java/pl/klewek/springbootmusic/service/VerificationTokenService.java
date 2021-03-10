package pl.klewek.springbootmusic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.VerificationToken;
import pl.klewek.springbootmusic.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService implements IVerifTokenService{

    private VerificationTokenRepository tokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        tokenRepository.save(verificationToken);
    }

    public boolean isTokenExists(String token){
        if(tokenRepository.existsByToken(token)){
            return true;
        }
        return false;
    }

    public VerificationToken getVerificationToken(String token){
        VerificationToken verificationToken =  tokenRepository.findByToken(token);
        return verificationToken;
    }

    public VerificationToken getVerificationToken(User user){
        VerificationToken verificationToken =  tokenRepository.findByUser(user);
        return verificationToken;
    }

}
