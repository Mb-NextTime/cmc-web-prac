package tickets.booking.avia.dao;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import tickets.booking.avia.SessionFactoryHolder;
import tickets.booking.avia.entities.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FlightDaoTest {

    private Transaction tr;

    @BeforeAll
    static void beforeAll() {
        SessionFactoryHolder.sf = new Configuration()
                .addAnnotatedClass(Aircraft.class)
                .addAnnotatedClass(Booking.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Flight.class)
                .addAnnotatedClass(Seat.class)
                .buildSessionFactory();
    }

    @AfterAll
    static void afterAll() {
        SessionFactoryHolder.sf.close();
    }

    @BeforeEach
    void setUp() {
        tr = SessionFactoryHolder.sf.getCurrentSession().beginTransaction();
    }

    @AfterEach
    void tearDown() {
        tr.rollback();
    }

    @Test
    void findById() {
        var dao = new FlightDao();
        var fl = dao.findById(1);
        assertNotNull(fl);
        assertEquals(1, fl.getId());
        assertEquals("FFlights", fl.getCompany());

        // check non-existing object
        assertNull(dao.findById(100000));
    }

    @Test
    void delete() {
        var dao = new FlightDao();
        var fl = dao.findById(1);
        dao.delete(fl);
        assertNull(dao.findById(1));
    }

    @Test
    void insert() {
        var dao = new FlightDao();

        var nEntities = dao.findAll().size();
        var flExample = dao.findById(1);

        var newFlight = new Flight();
        newFlight.setCompany("testCompany");
        var now = new Timestamp(System.currentTimeMillis());
        newFlight.setScheduledArrival(now);
        newFlight.setScheduledDeparture(now);
        newFlight.setBasePrice(100);

        newFlight.setAircraft(flExample.getAircraft());
        newFlight.setDepartureCity(flExample.getDepartureCity());
        newFlight.setArrivalCity(flExample.getArrivalCity());

        dao.insert(newFlight);

        assertEquals(nEntities + 1, dao.findAll().size());

        var flight = dao.findById(newFlight.getId());
        assertNotNull(flight);
        assertEquals("testCompany", flight.getCompany());
        assertEquals(now, flight.getScheduledDeparture());
        assertEquals(now, flight.getScheduledArrival());
        assertEquals(100, flight.getBasePrice());
    }

    @Test
    void update() {
        var dao = new FlightDao();
        var fl = dao.findById(1);
        assertNotEquals("testCompany", fl.getCompany());
        fl.setCompany("testCompany");
        dao.update(fl);
        fl = dao.findById(1);
        assertEquals("testCompany", fl.getCompany());
    }

    @Test
    void filterByTime() {
        var dao = new FlightDao();
        var flights = dao.filter(null, null, null, null);
        assertEquals(dao.findAll().size(), flights.size());

        flights = dao.filter(Timestamp.valueOf("2023-11-19 00:00:00"), null, null, null);
        assertEquals(6, flights.size());

        flights = dao.filter(null, Timestamp.valueOf("2023-11-19 00:00:00"), null, null);
        assertEquals(9, flights.size());

        flights = dao.filter(null, null, "Oslo", null);
        assertEquals(1, flights.size());

        flights = dao.filter(null, null, null, "Stockholm");
        assertEquals(9, flights.size());

        flights = dao.filter(Timestamp.valueOf("2023-11-17 00:00:00"),
                Timestamp.valueOf("2023-11-19 00:00:00"),
                null, null);
        assertEquals(5, flights.size());
            assertArrayEquals(new Integer[]{1, 7, 9, 10, 15}, flights.stream().map(Flight::getId).sorted().toArray());
    }
}