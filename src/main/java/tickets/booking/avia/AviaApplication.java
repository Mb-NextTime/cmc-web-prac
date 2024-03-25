package tickets.booking.avia;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tickets.booking.avia.dao.BookingDao;
import tickets.booking.avia.dao.CustomerDao;
import tickets.booking.avia.dao.FlightDao;
import tickets.booking.avia.entities.*;

import java.util.List;

class AviaApplication {
	public static void main(String[] args) {
		SessionFactoryHolder.sf = new Configuration()
				.addAnnotatedClass(Aircraft.class)
				.addAnnotatedClass(Booking.class)
				.addAnnotatedClass(City.class)
				.addAnnotatedClass(Customer.class)
				.addAnnotatedClass(Flight.class)
				.addAnnotatedClass(Seat.class)
				.buildSessionFactory();
		Transaction tr = SessionFactoryHolder.sf.getCurrentSession().beginTransaction();
		CustomerDao dao = new CustomerDao();
		List<Customer> customers = dao.findAll();
		for (Customer customer : customers) {
			System.out.println("ID: " + customer.getId() + ", Name: " + customer.getName() + ", bookings:");
			for (Booking booking : customer.getBookings()) {
				System.out.println("\tID:" + booking.getId() + ", Price:" + booking.getPrice());
			}
		}
		System.out.println();

		var flightDao = new FlightDao();
		var flights = flightDao.findAll();
		for (var flight : flights) {
			System.out.println("ID: " + flight.getId() + ",  Company: " + flight.getCompany()
					+ ", Departure Time: " + flight.getScheduledDeparture().toString() + ", seats: ");
			for (var booking : flight.getBookings()) {
				var seat = booking.getSeat();
				System.out.println("\tID:" + seat.getId() + ", row:" + seat.getRow()
						+ ", column: " + seat.getColumn());
			}
		}
		tr.rollback();
		SessionFactoryHolder.sf.close();
	}
}
