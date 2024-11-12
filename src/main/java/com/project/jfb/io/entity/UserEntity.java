package com.project.jfb.io.entity;


import jakarta.persistence.*;
import lombok.*;
import com.project.jfb.io.entity.enums.UserRole;
import com.project.jfb.view.Printable;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private UUID userId = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Timestamp creationDate;

    @Column(nullable = false)
    //private UserRole role = UserRole.CLIENT;
    private String userRole = "Client";

/*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;

 */
}
