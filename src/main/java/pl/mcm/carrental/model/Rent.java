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
    @Column(name = "id")
    private Long id;

    @Column(name = "car_id")
    private long carID;

    @Column(name = "user_id")
    private long userID;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "total_cost")
    private double totalCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalstatus")
    private RentStatus rentStatus;
}
