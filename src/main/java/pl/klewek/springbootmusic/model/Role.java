package pl.klewek.springbootmusic.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String roleName;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoles;

}
