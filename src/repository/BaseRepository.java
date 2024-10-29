package repository;

import exception.NotFoundException;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface BaseRepository<T> {
    Set<T> findAll() throws SQLException;

    void add(T entity) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(Long id) throws SQLException, NotFoundException;

    Optional<T> findById(Long id) throws SQLException;
}
