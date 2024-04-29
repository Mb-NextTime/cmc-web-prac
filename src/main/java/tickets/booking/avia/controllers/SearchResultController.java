package tickets.booking.avia.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tickets.booking.avia.services.FlightService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/search")
public class SearchResultController {

    @Autowired
    private FlightService flightService;

    @PostMapping("/submit")
    public String searchSubmit(Model model, @RequestParam String dCity, @RequestParam String aCity,
                               @RequestParam String dDate, @RequestParam String aDate,
                               RedirectAttributes redirectAttributes)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        Timestamp dStamp = null;
        Timestamp aStamp = null;
        try {
            dStamp = new Timestamp(sdf.parse(dDate).getTime());
            aStamp = new Timestamp(sdf.parse(aDate).getTime());
        } catch (ParseException ignored) {
        }

        var flights = flightService.findWithFilter(dStamp, aStamp, dCity, aCity);

        redirectAttributes.addFlashAttribute("flights", flights);
        redirectAttributes.addFlashAttribute("dCity", dCity);
        redirectAttributes.addFlashAttribute("aCity", aCity);
        redirectAttributes.addFlashAttribute("dDate", dDate);
        redirectAttributes.addFlashAttribute("aDate", aDate);

        return "redirect:/search/result";
    }

    @GetMapping("/result")
    public String searchResult(Model model)
    {
        return "search-result";
    }
}
