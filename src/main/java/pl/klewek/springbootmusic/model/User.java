package pl.klewek.springbootmusic.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @NonNull
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> roles;

}
