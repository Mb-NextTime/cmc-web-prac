package tickets.booking.avia.repositories.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tickets.booking.avia.entities.Flight;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FlightRepositoryCustomImpl implements FlightRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flight> findWithFilter(Timestamp timeLow, Timestamp timeHigh, String departureCity,
                                String arrivalCity) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Flight> criteria = cb.createQuery(Flight.class);

        Root<Flight> root = criteria.from(Flight.class);
        ArrayList<Predicate> preds = new ArrayList<>();
        if (timeLow != null) {
            preds.add(cb.greaterThanOrEqualTo(root.get("scheduledDeparture"), timeLow));
        }
        if (timeHigh != null) {
            preds.add(cb.lessThan(root.get("scheduledDeparture"), timeHigh));
        }
        if (departureCity != null) {
            preds.add(cb.equal(root.get("departureCity").get("name"), departureCity));
        }
        if (arrivalCity != null) {
            preds.add(cb.equal(root.get("arrivalCity").get("name"), arrivalCity));
        }
        criteria.where(preds.toArray(Predicate[]::new));

        return entityManager.createQuery(criteria).getResultList();
    }
}
