package model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.UserRole;
import view.Printable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class User implements Printable {

    @Id
    private UUID id = UUID.randomUUID();

    @Column //(name = "name")
    private String name;

    @Column //(name = "creation_date")
    private Timestamp creationDate;

    @Column //(name = "user_role")
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "id")
    private List<Ticket> tickets;

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
