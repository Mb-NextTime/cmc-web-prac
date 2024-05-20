package tickets.booking.avia.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import tickets.booking.avia.entities.Customer;
import tickets.booking.avia.repositories.CustomerRepository;

import java.util.Optional;

@Service
public class CustomerService extends GenericService<Customer, CustomerRepository> {
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<Customer> loginCustomer(String email, String password) {
        var result = repository.findByEmail(email);
        if (result.isEmpty()) {
            return result;
        }
        if (BCrypt.verifyer().verify(password.toCharArray(), result.get().getPasswdHash()).verified) {
            return result;
        }
        else {
            return Optional.empty();
        }
    }

    public boolean registerCustomer(String email, String password, String name) {
        if (repository.findByEmail(email).isPresent()) {
            return false;
        }
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        Customer customer = Customer.builder().email(email).passwdHash(bcryptHashString).name(name)
                .bonuses(0L).build();
        repository.save(customer);
        return true;
    }
}
