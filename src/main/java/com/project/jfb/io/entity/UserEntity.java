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
@Table(name="users_info")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Timestamp creationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CLIENT;

    @OneToMany(mappedBy = "user_id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Transient
    private List<TicketEntity> tickets;

}
