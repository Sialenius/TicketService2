package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {

    Optional<T> fetchByID(UUID id);

    ArrayList<T> getAll();

    void save(T t);

    void delete(UUID uuid);

    void close() throws SQLException;

}
