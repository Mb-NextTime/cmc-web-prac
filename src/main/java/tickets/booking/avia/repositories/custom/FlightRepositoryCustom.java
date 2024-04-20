package tickets.booking.avia.repositories.custom;

import tickets.booking.avia.entities.Flight;

import java.sql.Timestamp;
import java.util.List;

public interface FlightRepositoryCustom {
    List<Flight> findWithFilter(Timestamp timeLow, Timestamp timeHigh, String departureCity,
                                String arrivalCity);
}
