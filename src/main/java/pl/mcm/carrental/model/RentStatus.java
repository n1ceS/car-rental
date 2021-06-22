package pl.mcm.carrental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "rentalstatuscodes")
@Getter
@Setter
public class RentStatus implements Serializable {
    @Id
    @JoinColumn(name = "rent_status")
    private String rentStatus;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy="rentStatus", cascade = CascadeType.ALL)
    private Set<Rent> rents;
}
