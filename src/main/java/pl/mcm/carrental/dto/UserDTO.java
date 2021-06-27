package pl.mcm.carrental.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mcm.carrental.annotation.PasswordValueMatch;
import pl.mcm.carrental.annotation.ValidPassword;

import javax.validation.constraints.*;
import java.util.Date;

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "repeatedPassword",
                message = "Passwords do not match!"
        )
})
@Data
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(min = 6, max = 20)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword
    private String password;

    @NotBlank
    @Size(min = 6, max = 20)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword
    private String repeatedPassword;

    @NotBlank
    @Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}\\b", message = "Input have to be a email!")
    @Size(max = 255)
    private String email;

    @Size(max = 20)
    private String phone;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date birthDate;

    @NotBlank
    @Length(max = 11, message = "Pesel must have max 11 digits")
    private String pesel;

    public UserDTO() {
    }

}
