package pl.klewek.springbootmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klewek.springbootmusic.model.User;
import pl.klewek.springbootmusic.model.UserRole;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAllByUser(User user);

    User findByUser(User user);
}
