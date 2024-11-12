package com.project.jfb.service;

import com.project.jfb.shared.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getUserById(UUID userUd);

    UserDto getUserByName(String userName);

    List<UserDto> getAllUsers();

    UserDto saveUser(UserDto userDto);

    void deleteUserById(UUID userId);




}
