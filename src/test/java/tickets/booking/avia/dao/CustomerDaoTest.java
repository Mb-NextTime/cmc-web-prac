package tickets.booking.avia.dao;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.*;
import tickets.booking.avia.SessionFactoryHolder;
import tickets.booking.avia.entities.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDaoTest {

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
    void findByEmail() {
        var dao = new CustomerDao();
        var fl = dao.findByEmail("max@mail.com");
        assertNotNull(fl);
        assertEquals("Max", fl.getName());
    }
}