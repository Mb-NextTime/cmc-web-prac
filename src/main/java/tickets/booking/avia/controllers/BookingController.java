package tickets.booking.avia.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tickets.booking.avia.entities.Booking;
import tickets.booking.avia.entities.Customer;
import tickets.booking.avia.entities.Flight;
import tickets.booking.avia.entities.Seat;
import tickets.booking.avia.services.BookingService;
import tickets.booking.avia.services.CustomerService;
import tickets.booking.avia.services.FlightService;
import tickets.booking.avia.services.SeatService;

import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private SeatService seatService;

    @PostMapping("/submit")
    public String submit(Model model,
                         @RequestParam Map<String, String> formData,
                         @CookieValue(defaultValue="") String email,
                         @CookieValue(defaultValue="") String password)
    {
        Customer customer = customerService.loginCustomer(email, password).orElse(null);

        Flight flight = flightService.findById(Integer.valueOf(formData.get("flightId"))).orElse(null);

        for (Map.Entry<String, String> entry : formData.entrySet()) {
            if (Objects.equals(entry.getValue(), "on")) {
                String[] parts = entry.getKey().split("_");
                Integer row = Integer.valueOf(parts[0]);
                System.out.println('A' + row);
                Integer column = Integer.valueOf(parts[1]);
                Seat seat = seatService.findSimilar(Example.of(Seat.builder()
                        .aircraft(flight.getAircraft()).row(row).column(column).build())).orElse(null);
                Booking newBooking = Booking.builder().flight(flight).customer(customer).price(flight.getBasePrice())
                        .seat(seat).build();
                bookingService.save(newBooking);
            }
        }
        return "redirect:/profile";
    }
}
