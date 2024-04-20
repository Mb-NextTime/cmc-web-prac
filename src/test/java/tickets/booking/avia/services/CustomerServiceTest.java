package tickets.booking.avia.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void findByEmail() {
        var customer = customerService.findByEmail("max@mail.com").orElse(null);
        assertNotNull(customer);
        assertEquals("Max", customer.getName());
    }
}