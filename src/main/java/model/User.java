package model;


import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import lombok.Data;
import model.enums.UserRole;
import org.hibernate.annotations.CreationTimestamp;
import view.Printable;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users_info")
public class User implements Printable {

    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column (name = "name", nullable = false)
    private String name;

    @Column (name = "creation_date", nullable = false)
    private Timestamp creationDate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Ticket> tickets = new ArrayList<>();

    @Transient
    private UserRole role;

    public User() {
    }

    public User (String name) {
        this.name = name;
    }

    public User (String name, Timestamp creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public void printRole() {}


    @Override
    public String toString() {
        return  "============= USER =============" + '\n' +
                "ID: " + this.getId() + '\n' +
                "Name: " + this.getName() + '\n' +
                "Creation date: " + this.getCreationDate() + '\n' +
                "User role: " + this.getClass().getSimpleName();

    }


}
