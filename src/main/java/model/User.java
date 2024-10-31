package model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.UserRole;
import view.Printable;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public abstract class User extends Entity implements Printable {
    private String name;
    private Timestamp creationDate;
    private UserRole role;


    public void printRole() {}

    @Override
    public String toString() {
        return  "============= USER =============" + '\n' +
                "ID: " + this.getID() + '\n' +
                "Name: " + this.getName() + '\n' +
                "Creation date: " + this.getCreationDate() + '\n' +
                "User role: " + this.getClass().getSimpleName();

    }


}
