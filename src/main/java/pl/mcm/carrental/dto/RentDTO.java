package pl.mcm.carrental.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

//    CarDTO albo sama marke i model

    private String brand;

    private String model;

    //UserDTO albo email

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

//    Daty

//    private Timestamp startDate;

//    private Timestamp endDate;

    private String rentStatus;

    private String description;
}
