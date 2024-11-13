package com.project.jfb.service;

import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.UserRole;
import com.project.jfb.repository.UserRepository;
import com.project.jfb.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toCollection;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto getUserById(UUID userUd) {

    UserDto returnValue = new UserDto();
    Optional<UserEntity> userEntity = userRepository.findById(userUd);

    BeanUtils.copyProperties(userEntity, returnValue);
    return returnValue;
    }

    public UserDto getUserByName(String userName) {

        UserEntity storedUser = userRepository.findByName(userName);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUser, returnValue);
        return returnValue;
    }

    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepository.findAll();
    }


    public UserDto saveUser(UserDto userDto) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        userEntity.setId(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        userEntity.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
        userEntity.setRole(UserRole.CLIENT);

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValue = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValue);

        return returnValue;
    }

    public void deleteUserById(UUID userId) {
        userRepository.deleteById(userId);
    }

}
