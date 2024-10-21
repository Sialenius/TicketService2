package model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {

    Optional<T> getByID(UUID id);

    ArrayList<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);
    
}
