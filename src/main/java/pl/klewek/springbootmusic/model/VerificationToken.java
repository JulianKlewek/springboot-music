package pl.klewek.springbootmusic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class VerificationToken {

    private static final int TOKEN_DURATION_IN_HOURS = 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    private String token;

    private ZonedDateTime generationTime;

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.generationTime = ZonedDateTime.now();
    }

    public boolean isTokenExpired(ZonedDateTime actualTime){
        if(actualTime.isAfter(getExpirationDate())){
            return true;
        }
        return false;
    }

    private ZonedDateTime getExpirationDate(){
        ZonedDateTime expirationTime = ZonedDateTime.now();
        expirationTime.plus(TOKEN_DURATION_IN_HOURS, ChronoUnit.HOURS);
        return expirationTime;
    }

}
