package ua.mamchur.servletproject.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    void save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
    void close();
}