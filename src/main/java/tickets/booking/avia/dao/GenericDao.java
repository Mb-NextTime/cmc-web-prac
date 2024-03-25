package tickets.booking.avia.dao;
// source: https://gist.github.com/bytestree/6134f81a6ba6a66460569a0ecd596d37

import java.io.Serializable;
import java.util.List;

/**
 * Interface to provide common DAO methods
 *
 */
public interface GenericDao<E>
{
    /**
     *
     * @param entity: entity to delete
     */
    void delete( E entity );

    /**
     * Delete all records
     */
    void deleteAll();

    /**
     * Find all records
     */
    List<E> findAll();

    /**
     * Find by primary key
     * @param id
     * @return unique entity
     */
    E findById( Serializable id );

    /**
     * Insert entity
     * @param entity entity to insert
     */
    void insert( E entity );

    /**
     * Update entity
     * @param entity entity to update
     */
    E update( E entity );

}