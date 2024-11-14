package com.project.jfb.service;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.io.entity.enums.UserRole;
import com.project.jfb.model.response.UserRest;
import com.project.jfb.repository.TicketRepository;
import com.project.jfb.repository.UserRepository;
import com.project.jfb.shared.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    public UserDto getUserById(UUID userId) {

        UserDto returnValue = new UserDto();
        UserEntity userEntity =  userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " was not found."));

        BeanUtils.copyProperties(userEntity, returnValue); // Why copyProperties() didn't work with Optional<UserEntity>?

        return returnValue;
    }

    public List<UserDto> getAllUsers() {

        List<UserDto> allUsers = new ArrayList<>();

        for (UserEntity u: userRepository.findAll()) {
            UserDto tempDto = new UserDto();
            BeanUtils.copyProperties(u, tempDto);
            allUsers.add(tempDto);
        }

        return allUsers;
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

    @Transactional
    public void updateUserId(UserDto user, UUID newId) {
        userRepository.updateUserId(user.getId(), newId);
    }

    @Transactional
    public void updateUserAndCreateNewTicket(UserDto user, UUID newId) {

        updateUserId(user, newId);

        TicketEntity createdTicket = new TicketEntity();
        createdTicket.setUserId(newId);
        createdTicket.setTicketType(TicketType.DAY);

        ticketRepository.save(createdTicket);

    }

    @Transactional
    public void deleteUserById(UUID userId) {

        userRepository.deleteById(userId);
    }

}
