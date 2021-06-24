package pl.mcm.carrental.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mcm.carrental.model.RentStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class RentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;


    @NotNull(message = "Car id cannot be null")
    @Min(value = 0, message = "Car Id must be equal or greater than 0")
    private Long carID;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long userID;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @JsonFormat(pattern = "MM/dd/yyyy HH:MM")
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalCost;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String rentStatus;

    public RentDTO() {
    }
}
