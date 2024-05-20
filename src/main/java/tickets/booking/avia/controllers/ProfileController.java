package tickets.booking.avia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tickets.booking.avia.entities.Customer;
import tickets.booking.avia.services.CustomerService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public String profile(Model model,
                          @CookieValue(name="email", defaultValue="") String email,
                          @CookieValue(name="password", defaultValue="") String password) {
        Customer customer = customerService.loginCustomer(email, password).orElse(null);
        if (customer == null) {
            return "redirect:/authentication/login";
        }
        model.addAttribute("customer", customer);
        return "profile";
    }



}
