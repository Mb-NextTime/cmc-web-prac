package tickets.booking.avia.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Test
    @Transactional
    void deleteAll() {
        var nEntities = bookingService.list().size();
        assertNotEquals(0, nEntities);

        bookingService.deleteAll();
        nEntities = bookingService.list().size();
        assertEquals(0, nEntities);
    }
}