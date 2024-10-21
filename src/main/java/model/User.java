package model;


import model.enums.UserRole;
import view.Printable;

import java.time.LocalDate;

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

    public String getRole() {
        return role.getRole();
    }

    protected void setRole(UserRole role) {
        this.role = role;
    }

    public abstract void printRole();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '\n' +
                "ID: " + this.getId() + '\n' +
                "Name: " + this.getName() + '\n' +
                "Creation date: " + this.getCreationDate() + '\n';
    }

}
