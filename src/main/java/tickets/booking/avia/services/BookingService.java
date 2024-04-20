package tickets.booking.avia.services;

import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Booking;
import tickets.booking.avia.repositories.BookingRepository;

@Service
public class BookingService extends GenericService<Booking, BookingRepository> {
}
