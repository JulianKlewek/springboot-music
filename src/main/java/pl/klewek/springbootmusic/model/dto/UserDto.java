package pl.klewek.springbootmusic.model.dto;


import lombok.Getter;
import lombok.Setter;
import pl.klewek.springbootmusic.validation.ValidEmail;
import pl.klewek.springbootmusic.validation.ValidPassword;
import pl.klewek.springbootmusic.validation.ValidPasswordMatches;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ValidPasswordMatches
public class UserDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @ValidPassword
    private String password;

    @NotNull
    private String matchingPassword;

    @ValidEmail
    @NotNull
    private String email;

}
