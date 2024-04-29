package tickets.booking.avia.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "passwd_hash", nullable = false)
    private Long passwdHash;

    @Column(name = "bonuses", nullable = false)
    private Long bonuses;

    @OneToMany(mappedBy = "customer")
    private List<Booking> bookings;
}
