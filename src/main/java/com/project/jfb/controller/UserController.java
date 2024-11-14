package com.project.jfb.controller;

import com.project.jfb.model.request.UserUpdateIdRequestModel;
import com.project.jfb.service.UserService;
import com.project.jfb.shared.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users") //http://localhost:8081/users
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable UUID userId) {

        return userService.getUserById(userId);
    }


    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDetails) {

        return userService.saveUser(userDetails);
    }

    @PostMapping("/new")
    public UserDto createSpecifiedUser() {

        return userService.saveDefaultUser();
    }

    @PutMapping("/{userId}/new-id-and-ticket")
    public void updateUserIaAndCreateTicket(@PathVariable UUID userId, @RequestBody UserUpdateIdRequestModel userUpdateIdRequestModel) {

        UserDto userDto = userService.getUserById(userId);
        userService.updateUserAndCreateNewTicket(userDto, userUpdateIdRequestModel.getNewId());

    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable UUID userId,@RequestBody UserUpdateIdRequestModel userUpdateIdRequestModel) {

        UserDto userDto = userService.getUserById(userId);
        userService.updateUserId(userDto, userUpdateIdRequestModel.getNewId());

    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable UUID userId) {

        userService.deleteUserById(userId);
    }


}
