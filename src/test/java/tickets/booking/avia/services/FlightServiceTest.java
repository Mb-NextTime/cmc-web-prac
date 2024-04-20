package tickets.booking.avia.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import tickets.booking.avia.entities.*;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @Test
    void findById() {
        var fl = flightService.findById(1).orElse(null);
        assertNotNull(fl);
        assertEquals(1, fl.getId());
        assertEquals("FFlights", fl.getCompany());

        // check non-existing object
        assertNull(flightService.findById(100000).orElse(null));
    }

    @Test
    @Transactional
    void delete() {
        var fl = flightService.findById(1).orElse(null);
        assertNotNull(fl);
        flightService.delete(fl);
        assertNull(flightService.findById(1).orElse(null));
    }

    @Test
    @Transactional
    void insert() {
        var nEntities = flightService.list().size();
        var flExample = flightService.findById(1).orElse(null);
        assertNotNull(flExample);

        var now = new Timestamp(System.currentTimeMillis());

        var newFlight = Flight.builder()
                .company("testCompany")
                .scheduledDeparture(now)
                .scheduledArrival(now)
                .basePrice(100)
                .aircraft(flExample.getAircraft())
                .departureCity(flExample.getDepartureCity())
                .arrivalCity(flExample.getArrivalCity())
                .build();

        flightService.save(newFlight);

        assertEquals(nEntities + 1, flightService.list().size());

        var flight = flightService.findById(newFlight.getId()).orElse(null);
        assertNotNull(flight);
        assertEquals("testCompany", flight.getCompany());
        assertEquals(now, flight.getScheduledDeparture());
        assertEquals(now, flight.getScheduledArrival());
        assertEquals(100, flight.getBasePrice());
    }

    @Test
    @Transactional
    void update() {
        var fl = flightService.findById(1).orElse(null);
        assertNotNull(fl);
        assertNotEquals("testCompany", fl.getCompany());
        fl.setCompany("testCompany");
        flightService.save(fl);
        fl = flightService.findById(1).orElse(null);
        assertNotNull(fl);
        assertEquals("testCompany", fl.getCompany());
    }

    @Test
    void filter() {
        var flights = flightService.findWithFilter(null, null, null, null);
        assertEquals(flightService.list().size(), flights.size());

        flights = flightService.findWithFilter(Timestamp.valueOf("2023-11-19 00:00:00"), null, null, null);
        assertEquals(6, flights.size());

        flights = flightService.findWithFilter(null, Timestamp.valueOf("2023-11-19 00:00:00"), null, null);
        assertEquals(9, flights.size());

        flights = flightService.findWithFilter(null, null, "Oslo", null);
        assertEquals(1, flights.size());

        flights = flightService.findWithFilter(null, null, null, "Stockholm");
        assertEquals(9, flights.size());

        flights = flightService.findWithFilter(Timestamp.valueOf("2023-11-17 00:00:00"),
                Timestamp.valueOf("2023-11-19 00:00:00"),
                null, null);
        assertEquals(5, flights.size());
            assertArrayEquals(new Integer[]{1, 7, 9, 10, 15}, flights.stream().map(Flight::getId).sorted().toArray());
    }
}