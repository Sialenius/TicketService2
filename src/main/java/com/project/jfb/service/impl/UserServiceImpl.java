package com.project.jfb.service.impl;

import com.project.jfb.io.entity.Iteratorable;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.UserRole;
import com.project.jfb.repository.UserRepository;
import com.project.jfb.service.UserService;
import com.project.jfb.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto getUserById(UUID userUd) {

    UserDto returnValue = new UserDto();
    Optional<UserEntity> userEntity = userRepository.findById(userUd);

    BeanUtils.copyProperties(userEntity, returnValue);
    return returnValue;
    }

    @Override
    public UserDto getUserByName(String userName) {

        UserEntity storedUser = userRepository.findByName(userName);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUser, returnValue);
        return returnValue;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserEntity> test = (List<UserEntity>) userRepository.findAll();
        List<UserDto> returnValue = new ArrayList<>();

        BeanUtils.copyProperties(test, returnValue);

        return returnValue;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        //userEntity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        userEntity.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        userEntity.setRole(UserRole.CLIENT);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    @Override
    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

}
