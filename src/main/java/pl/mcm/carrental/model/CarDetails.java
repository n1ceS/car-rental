package pl.mcm.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cardetails")
@Data
@Builder
@AllArgsConstructor
public class CarDetails implements Serializable {

    @Id
    @Column(name = "car_id")
    private Long carID;

    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private int year;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "power")
    private String power;

    @Column(name = "gearbox")
    private String gearbox;

    @Column(name = "color")
    private String color;

    @Column(name = "photo")
    private String photo;

    @Column(name = "description")
    private String description;

    @Column(name = "doors_number")
    private int doorsNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    public CarDetails() {

    }
}
