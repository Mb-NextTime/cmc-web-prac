package tickets.booking.avia.dao;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import tickets.booking.avia.SessionFactoryHolder;
import tickets.booking.avia.entities.*;

import static org.junit.jupiter.api.Assertions.*;

class BookingDaoTest {

    private Transaction tr;

    @BeforeAll
    static void BeforeAll() {
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
    void deleteAll() {
        var dao = new BookingDao();

        var nEntities = dao.findAll().size();
        assertNotEquals(0, nEntities);

        dao.deleteAll();
        nEntities = dao.findAll().size();
        assertEquals(0, nEntities);
    }
}