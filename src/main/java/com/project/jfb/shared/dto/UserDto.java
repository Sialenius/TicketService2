package com.project.jfb.shared.dto;

import com.project.jfb.io.entity.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private UUID id;
    private String name;
    private Timestamp creationDate;
    private UserRole role;

}
