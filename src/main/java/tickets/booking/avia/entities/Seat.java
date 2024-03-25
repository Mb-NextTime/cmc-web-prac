package tickets.booking.avia.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @Column(name = "seat_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Column(name = "row", nullable = false)
    private Integer row;

    @Column(name = "column", nullable = false)
    private Integer column;
}
