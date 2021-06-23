package pl.mcm.carrental.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CarDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String type;

    @Max(value = 2021)
    private int year;

    @Size(max = 5, message = "The max length of status is 5!")
    private String status;

    @NotBlank
    private String fuelType;

    @NotBlank
    private String power;

    @NotBlank
    private String gearbox;

    @NotBlank
    private String color;

    @NotBlank
    private String photo;

    @NotBlank
    private String description;

    @NotNull
    @Min(3)
    @Max(7)
    private int doorsNumber;

    public CarDTO() {
    }
}
