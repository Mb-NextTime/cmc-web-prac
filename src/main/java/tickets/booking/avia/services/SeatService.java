package tickets.booking.avia.services;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Seat;
import tickets.booking.avia.repositories.SeatRepository;

import java.util.Optional;

@Service
public class SeatService extends GenericService<Seat, SeatRepository> {
    public Optional<Seat> findSimilar(Example<Seat> example) {
        return repository.findOne(example);
    }
}
