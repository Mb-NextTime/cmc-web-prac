package tickets.booking.avia.dao;
// source: https://gist.github.com/bytestree/6134f81a6ba6a66460569a0ecd596d37

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import tickets.booking.avia.SessionFactoryHolder;

@SuppressWarnings("unchecked")
public abstract class AbstractGenericDao<E> implements GenericDao<E> {

    private final Class<E> entityClass;

    public AbstractGenericDao() {
        this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    private final SessionFactory sessionFactory = SessionFactoryHolder.sf;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public E findById(final Serializable id) {
        return (E) getSession().get(this.entityClass, id);
    }

    @Override
    public void delete(E entity) {
        getSession().remove(entity);
    }

    @Override
    public void deleteAll() {
        List<E> entities = findAll();
        for (E entity : entities) {
            getSession().remove(entity);
        }
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder criteria_builder = getSession().getCriteriaBuilder();
        CriteriaQuery<E> criteria = criteria_builder.createQuery(this.entityClass);
        criteria.select(criteria.from(this.entityClass));
        return getSession().createQuery(criteria).getResultList();
    }

    @Override
    public void insert(E entity) {
        getSession().persist(entity);
    }

    @Override
    public E update(E entity) {
        return getSession().merge(entity);
    }

}