package tickets.booking.avia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.City;
import tickets.booking.avia.entities.Flight;
import tickets.booking.avia.repositories.CityRepository;
import tickets.booking.avia.repositories.FlightRepository;

import java.util.List;

@Service
public class CityService extends GenericService<City, CityRepository> {
}
