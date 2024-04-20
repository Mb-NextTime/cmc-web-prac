package tickets.booking.avia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickets.booking.avia.entities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
