package pl.mcm.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cardetails")
@Data
@Builder
@AllArgsConstructor
public class CarDetails {

    @Id
    @Column(name = "cars_ID")
    private Long carID;

    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private int year;

    @Column(name = "fuelType")
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

    @Column(name = "doorsNumber")
    private String doorsNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cars_ID")
    private Car car;

    public CarDetails() {

    }
}
