package model;


import model.enums.UserRole;
import view.Printable;

import java.time.LocalDate;
import java.util.UUID;

public abstract class User extends Entity implements Printable {
    private String name;
    private LocalDate creationDate;
    private UserRole role;

    public  String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    protected void setCreationDate(LocalDate date) {
        this.creationDate = date;
    }

    public UserRole getRole() {
        return role;
    }

    protected void setRole(UserRole role) {
        this.role = role;
    }

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
