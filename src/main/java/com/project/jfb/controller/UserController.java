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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users") //http://localhost:8081/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<UserRest> getAllUsers() {

        List<UserRest> returnValue = new ArrayList<>();

        for (UserDto u: userService.getAllUsers()) {
            UserRest tempUserRest = new UserRest();
            BeanUtils.copyProperties(u, tempUserRest);
            returnValue.add(tempUserRest);
        }
        return returnValue;
    }

    @GetMapping("/{userId}")
    public UserRest getUserById(@PathVariable UUID userId) {

        UserRest returnValue = new UserRest();
        BeanUtils.copyProperties(userService.getUserById(userId), returnValue);

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
