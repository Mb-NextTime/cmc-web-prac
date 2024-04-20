package tickets.booking.avia.services;

import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Aircraft;
import tickets.booking.avia.repositories.AircraftRepository;

@Service
public class AircraftService extends GenericService<Aircraft, AircraftRepository>{
}
