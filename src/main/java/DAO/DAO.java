package DAO;

import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface DAO<T> {

    public void save(T t);

    public void delete(@NonNull String id);
}
