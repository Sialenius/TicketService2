package com.project.jfb.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {

    T fetchByID(UUID id);

    List<T> getAll();

    void save(T t);

    void delete(UUID uuid);

}
