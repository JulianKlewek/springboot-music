package pl.klewek.springbootmusic.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NonNull
    private User user;

    @ManyToOne
    @NonNull
    private Role role;

//    public UserRole(User user, Role role) {
//        this.user = user;
//        this.role = role;
//    }
}
