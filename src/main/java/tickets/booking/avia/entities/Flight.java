package tickets.booking.avia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @Column(name = "flight_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "scheduled_departure", nullable = false)
    private Timestamp scheduledDeparture;

    @Column(name = "scheduled_arrival", nullable = false)
    private Timestamp scheduledArrival;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Column(name = "base_price", nullable = false)
    private Integer basePrice;

    @OneToMany(mappedBy = "flight")
    private Set<Booking> bookings;
}