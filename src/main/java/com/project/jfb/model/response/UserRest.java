package com.project.jfb.model.response;

import com.project.jfb.io.entity.enums.UserRole;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserRest {
    private UUID userId;
    private String name;
    private Timestamp creationDate;
    private UserRole userRole;
}