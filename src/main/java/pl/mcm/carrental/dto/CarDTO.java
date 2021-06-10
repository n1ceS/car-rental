package pl.mcm.carrental.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class CarDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String brand;

    private String model;

    private BigDecimal price;

    private String type;

    private int year;

    private String status;

    private String fuelType;

    private String power;

    private String gearbox;

    private String color;

    private String photo;

    private String description;

    private String doorsNumber;

    public CarDTO() {
    }
}
