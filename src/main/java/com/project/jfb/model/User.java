package model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;
import lombok.*;
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
@AllArgsConstructor
@ToString
public abstract class User implements Printable {

    @Id
    private UUID id = UUID.randomUUID();

    private String name;

    private Timestamp creationDate;

    @Column
    private UserRole role;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

    public void printRole() {}

}
