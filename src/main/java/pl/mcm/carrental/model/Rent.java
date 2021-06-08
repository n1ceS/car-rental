package pl.mcm.carrental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Rentals")
@Getter
@Setter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "carID")
    private long carID;

    @Column(name = "userID")
    private long userID;

    @Column(name = "startDate")
    private Timestamp startDate;

    @Column(name = "endDate")
    private Timestamp endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalstatus")
    private RentStatus rentStatus;
}
