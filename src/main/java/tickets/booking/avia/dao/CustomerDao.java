package tickets.booking.avia.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import tickets.booking.avia.entities.Customer;
import tickets.booking.avia.entities.Flight;

import java.security.Timestamp;

public class CustomerDao extends AbstractGenericDao<Customer> {
    public Customer findByEmail(String email) {
        CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Customer> criteria = criteria_builder.createQuery(Customer.class);

        Root<Customer> root = criteria.from(Customer.class);
        criteria.where(criteria_builder.equal(root.get("email"), email));
        return getSession().createQuery(criteria).getSingleResultOrNull();
    }
}
