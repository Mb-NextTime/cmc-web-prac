package tickets.booking.avia.dao;

import jakarta.persistence.criteria.*;
import tickets.booking.avia.entities.Aircraft;
import tickets.booking.avia.entities.City;
import tickets.booking.avia.entities.Flight;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FlightDao extends AbstractGenericDao<Flight> {
    public List<Flight> filter(Timestamp timeLow, Timestamp timeHigh, String departureCity,
                                     String arrivalCity) {
        CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Flight> criteria = criteria_builder.createQuery(Flight.class);

        Root<Flight> root = criteria.from(Flight.class);
        ArrayList<Predicate> preds = new ArrayList<>();
        if (timeLow != null) {
            preds.add(criteria_builder.greaterThanOrEqualTo(root.get("scheduledDeparture"), timeLow));
        }
        if (timeHigh != null) {
            preds.add(criteria_builder.lessThan(root.get("scheduledDeparture"), timeHigh));
        }
        if (departureCity != null) {
            preds.add(criteria_builder.equal(root.get("departureCity").get("name"), departureCity));
        }
        if (arrivalCity != null) {
            preds.add(criteria_builder.equal(root.get("arrivalCity").get("name"), arrivalCity));
        }
        criteria.where(preds.toArray(Predicate[]::new));

        return getSession().createQuery(criteria).getResultList();
    }
}
