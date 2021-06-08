package pl.mcm.carrental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rentalstatuscodes")
@Getter
@Setter
public class RentStatus {
    @Id
    @JoinColumn(name = "rentstatus")
    private String rentStatus;

    @Column(name = "description")
    private String description;

}
