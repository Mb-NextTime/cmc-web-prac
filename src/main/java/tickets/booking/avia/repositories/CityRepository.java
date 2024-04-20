package tickets.booking.avia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tickets.booking.avia.entities.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
