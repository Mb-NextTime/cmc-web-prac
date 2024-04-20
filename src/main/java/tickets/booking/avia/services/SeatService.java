package tickets.booking.avia.services;

import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Seat;
import tickets.booking.avia.repositories.SeatRepository;

@Service
public class SeatService extends GenericService<Seat, SeatRepository> {
}
