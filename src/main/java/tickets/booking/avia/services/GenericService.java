package tickets.booking.avia.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tickets.booking.avia.entities.City;
import tickets.booking.avia.entities.Flight;
import tickets.booking.avia.repositories.CityRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<E, R extends JpaRepository<E, Integer>> {

    @Autowired
    protected R repository;

    public List<E> list() {
        return repository.findAll();
    }

    public Optional<E> findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    public void delete(E entity) {
        repository.delete(entity);
    }
}
