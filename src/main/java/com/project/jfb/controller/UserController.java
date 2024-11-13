package com.project.jfb.controller;

import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.model.request.UserDetailsRequestModel;
import com.project.jfb.model.request.UserUpdateIdRequestModel;
import com.project.jfb.model.response.UserRest;
import com.project.jfb.service.UserService;
import com.project.jfb.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users") //http://localhost:8081/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getAllUsers() {
        String returnValue = "";

        for (UserEntity userEntity: userService.getAllUsers()) {
            returnValue += userEntity;
            returnValue += '\n';
        }

        return returnValue;
    }

    @GetMapping("/{id}")
    public UserRest getUserById(@PathVariable UUID id) {

        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userService.getUserById(id), returnValue);

        return returnValue;
    }


    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserRest returnValue = new UserRest();

        UserDto createdUser = userService.saveUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;

    }

    @PostMapping("/new")
    public UserRest createSpecifiedUser() {

        UserDto userDto = new UserDto();
        userDto.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        userDto.setName("TEST");

        UserRest returnValue = new UserRest();

        UserDto createdUser = userService.saveUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;

    }


    @PutMapping("/{id}/new-id-and-ticket")
    public String updateUserIaAndCreateTicket(@PathVariable UUID id, @RequestBody UserUpdateIdRequestModel userUpdateIdRequestModel) {

        UserDto userDto = userService.getUserById(id);
        userService.updateUserAndCreateNewTicket(userDto, userUpdateIdRequestModel.getNewId());

        return "Update user ID and create new ticket was called";
    }


    @PutMapping("/{id}")
    public String updateUser(@PathVariable UUID id,@RequestBody UserUpdateIdRequestModel userUpdateIdRequestModel) {

        UserDto userDto = userService.getUserById(id);
        userService.updateUserId(userDto, userUpdateIdRequestModel.getNewId());

        return "Update user was called";

    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return "Delete user was called";
    }


}
