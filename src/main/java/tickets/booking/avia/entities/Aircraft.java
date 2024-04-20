package tickets.booking.avia.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @Column(name = "aircraft_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "n_rows", nullable = false)
    private Integer nRows;

    @Column(name = "n_cols", nullable = false)
    private Integer nCols;
}
