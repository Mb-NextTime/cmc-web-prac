package tickets.booking.avia.services;

import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Customer;
import tickets.booking.avia.repositories.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService extends GenericService<Customer, CustomerRepository> {
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
