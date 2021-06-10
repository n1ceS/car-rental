package pl.mcm.carrental.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class UserDTO {

    @NotNull
    @Size(max = 50)
    private String firstname;

    @NotNull
    @Size(max = 50)
    private String lastname;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    @Size(min = 6, max = 20)
    private String repeatedPassword;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 20)
    private String phone;

    @NotNull
    private Date birthDate;
}
