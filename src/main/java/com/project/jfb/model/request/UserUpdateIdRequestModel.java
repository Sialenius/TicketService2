package com.project.jfb.model.request;

import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateIdRequestModel {
    private UUID newId;
}
