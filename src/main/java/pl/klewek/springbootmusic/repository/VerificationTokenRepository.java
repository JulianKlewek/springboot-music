package pl.klewek.springbootmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.VerificationToken;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByUser(User user);

    VerificationToken findByToken(String token);

    boolean existsByToken(String token);
}
