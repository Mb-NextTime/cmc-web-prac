package tickets.booking.avia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickets.booking.avia.entities.Flight;
import tickets.booking.avia.repositories.custom.FlightRepositoryCustom;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer>, FlightRepositoryCustom {
}
