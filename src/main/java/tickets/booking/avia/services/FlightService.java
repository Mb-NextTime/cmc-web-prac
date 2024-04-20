package tickets.booking.avia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Flight;
import tickets.booking.avia.repositories.FlightRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService extends GenericService<Flight, FlightRepository> {
    public List<Flight> findWithFilter(Timestamp timeLow, Timestamp timeHigh, String departureCity,
                                String arrivalCity) {
        return repository.findWithFilter(timeLow, timeHigh, departureCity, arrivalCity);
    }
}
