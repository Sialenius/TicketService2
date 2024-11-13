package com.project.jfb.model.request;

import com.project.jfb.io.entity.enums.UserRole;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserDetailsRequestModel {
    private UUID id;
    private String name;
    private Timestamp creationDate;
    private UserRole userRole;
}