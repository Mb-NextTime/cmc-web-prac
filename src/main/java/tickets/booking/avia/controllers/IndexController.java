package tickets.booking.avia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tickets.booking.avia.services.CustomerService;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public String searchPage(Model model,
                             @CookieValue(defaultValue="") String email,
                             @CookieValue(defaultValue="") String password)
    {
        model.addAttribute("user", customerService.loginCustomer(email, password).orElse(null));
        return "index";
    }
}
