package pl.mcm.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "carstatus")
@Getter
@Setter
public class CarStatus implements Serializable {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "car_status" )
    @NotBlank
    private String carStatus;

    @NotBlank
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy="carStatus")
    private Set<Car> cars;
}
