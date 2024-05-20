package tickets.booking.avia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tickets.booking.avia.entities.Booking;
import tickets.booking.avia.entities.Seat;
import tickets.booking.avia.services.CustomerService;
import tickets.booking.avia.services.FlightService;

import java.util.Arrays;

@Controller
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public String viewFlight(Model model, @PathVariable Integer id,
                             @CookieValue(defaultValue="") String email,
                             @CookieValue(defaultValue="") String password)
    {
        model.addAttribute("user", customerService.loginCustomer(email, password).orElse(null));
        var flight = flightService.findById(id).orElse(null);
        model.addAttribute("flight", flight);
        Integer nRows = flight.getAircraft().getNRows();
        Integer nCols = flight.getAircraft().getNCols();
        Boolean[][] booked = new Boolean[nRows][nCols];

        for (var row: booked) {
            Arrays.fill(row, false);
        }

        for (Booking booking: flight.getBookings()) {
            Seat seat = booking.getSeat();
            booked[seat.getRow()][seat.getColumn()] = true;
        }

        model.addAttribute("bookings", booked);
        return "flight";
    }
}
