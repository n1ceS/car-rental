package pl.mcm.carrental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status")
    private String carStatus;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private CarDetails carDetails;


}