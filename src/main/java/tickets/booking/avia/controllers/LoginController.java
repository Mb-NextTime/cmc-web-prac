package tickets.booking.avia.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tickets.booking.avia.services.CustomerService;

import java.io.IOException;

@Controller
@RequestMapping("/authentication")
public class LoginController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/submit")
    public void submit(HttpServletResponse response, @RequestParam String email, @RequestParam String password) throws IOException {
        if (customerService.loginCustomer(email, password).isEmpty()) {
            response.sendRedirect("login");
            return;
        }
        else {
            System.out.println("Login successful");
        }

        Cookie emailCookie = new Cookie("email", email);
        emailCookie.setMaxAge(84000);
        emailCookie.setPath("/");
        Cookie passwordCookie = new Cookie("password", password);
        passwordCookie.setMaxAge(84000);
        passwordCookie.setPath("/");
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        response.sendRedirect("/");
    }

    @GetMapping("/register")
    public String register() { return "register"; }

    @PostMapping("/submit-register")
    public void submit(HttpServletResponse response, @RequestParam String email, @RequestParam String password, @RequestParam String name) throws IOException {
        if (!customerService.registerCustomer(email, password, name)) {
            System.out.println("Registration unsuccessful");
            response.sendRedirect("register");
            return;
        }
        Cookie emailCookie = new Cookie("email", email);
        emailCookie.setMaxAge(84000);
        emailCookie.setPath("/");
        Cookie passwordCookie = new Cookie("password", password);
        passwordCookie.setMaxAge(84000);
        passwordCookie.setPath("/");
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        response.sendRedirect("/");
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response,
                       @CookieValue(name="email", defaultValue="") String email,
                       @CookieValue(name="password", defaultValue="") String password) throws IOException {
        Cookie emailCookie = new Cookie("email", "");
        emailCookie.setMaxAge(0);
        emailCookie.setPath("/");
        Cookie passwordCookie = new Cookie("password", "");
        passwordCookie.setMaxAge(0);
        passwordCookie.setPath("/");
        response.addCookie(emailCookie);
        response.addCookie(passwordCookie);
        response.sendRedirect("/");
    }

}

