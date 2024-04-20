package tickets.booking.avia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickets.booking.avia.entities.Aircraft;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {
}
