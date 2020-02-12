package ua.mamchur.servletproject.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> {
    void save(T entity);

    Optional<T> findById(Long id);

    List<T> findAll();

    void update(T entity);

    void delete(int id);
}