package com.project.jfb.controller;

import com.project.jfb.model.request.UserDetailsRequestModel;
import com.project.jfb.model.request.UserIdRequestModel;
import com.project.jfb.model.response.UserRest;
import com.project.jfb.service.UserService;
import com.project.jfb.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("users") //http://localhost:8081/users
public class UserController {

    @Autowired
    UserService userService;

    /*
    @GetMapping
    public UserRest getUserById(@RequestBody UserIdRequestModel userId) {

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userId, userDto);

        UserRest returnValue = new UserRest();

        UserDto createdUser = userService.getUserById(userId.getId());
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

     */

    @GetMapping
    public String getUser() {
        return "get user was called";

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

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser(UUID userId) {
        //userService.deleteUserById(UUID userId);
        return "delete user was called";
    }


}
