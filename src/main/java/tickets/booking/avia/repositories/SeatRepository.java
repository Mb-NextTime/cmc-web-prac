package tickets.booking.avia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickets.booking.avia.entities.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
}
