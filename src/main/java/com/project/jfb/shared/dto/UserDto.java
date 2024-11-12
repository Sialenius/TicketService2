package com.project.jfb.shared.dto;

import com.project.jfb.io.entity.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String name;
    private Timestamp creationDate;
    private UserRole userRole = UserRole.CLIENT;

}
