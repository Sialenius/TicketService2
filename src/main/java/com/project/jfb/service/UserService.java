package com.project.jfb.service;

import com.project.jfb.io.entity.TicketEntity;
import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import com.project.jfb.io.entity.enums.UserRole;
import com.project.jfb.repository.TicketRepository;
import com.project.jfb.repository.UserRepository;
import com.project.jfb.shared.dto.TicketDto;
import com.project.jfb.shared.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final TicketRepository ticketRepository;

    public UserDto getUserById(UUID userId) {

        UserDto returnValue = new UserDto();
        if (userRepository.findById(userId).isEmpty()) {
            log.info("USER WITH ID: " + userId + " WAS NOT FOUND.");
            return null;
         } else {
            UserEntity userEntity = userRepository.findById(userId).get();
            BeanUtils.copyProperties(userEntity, returnValue); // Why copyProperties() didn't work with Optional<UserEntity>?

            return returnValue;
        }
    }

    public List<UserDto> getAllUsers() {

        List<UserDto> allUsers = new ArrayList<>();

        for (UserEntity u : userRepository.findAll()) {
            UserDto tempDto = new UserDto();
            BeanUtils.copyProperties(u, tempDto);
            allUsers.add(tempDto);
        }

        return allUsers;
    }


    @Transactional
    public UserDto saveUser(UserDto userDto) {

        UserDto returnValue = new UserDto();

        if (userDto.getId() != null && !userRepository.findById(userDto.getId()).isEmpty()) {
            log.info("USER WITH ID: " + userDto.getId() + " IS ALREADY EXIST");
            return null;
        } else {
            UserEntity userEntity = new UserEntity();

            if (userDto.getRole() == null & userDto.getCreationDate() != null) {
                BeanUtils.copyProperties(userDto, userEntity, "role");
            } else if (userDto.getRole() != null & userDto.getCreationDate() == null) {
                BeanUtils.copyProperties(userDto, userEntity, "creationDate");
            } else if (userDto.getRole() == null & userDto.getCreationDate() == null) {
                BeanUtils.copyProperties(userDto, userEntity, "role", "creationDate");
            } else {
                BeanUtils.copyProperties(userDto, userEntity);
            }

            UserEntity createdUser = userRepository.save(userEntity);

            BeanUtils.copyProperties(createdUser, returnValue);

            log.info("USER WAS CREATED: " + returnValue);
            return returnValue;
        }

    }

    @Transactional
    public UserDto saveDefaultUser() {
        UserDto userDto = new UserDto();
        userDto.setName("DEFAULT");

        return saveUser(userDto);

    }

    @Transactional
    public void updateUserId(UserDto user, UUID newId) {
        userRepository.updateUserId(user.getId(), newId);
        log.info("USER WAS UPDATED: " + getUserById(newId));

    }

    @Transactional
    public void updateUserAndCreateNewTicket(UserDto user, UUID newId) {

        updateUserId(user, newId);

        TicketEntity createdTicket = new TicketEntity();
        createdTicket.setUserId(newId);
        createdTicket.setTicketType(TicketType.DAY);

        TicketDto createdTicketDto = new TicketDto();
        BeanUtils.copyProperties(createdTicket, createdTicketDto);

        ticketRepository.save(createdTicket);
        log.info("TICKET WAS CREATED: " + createdTicketDto.toString());

    }

    @Transactional
    public void deleteUserById(UUID userId) {
        UserDto deletedUserDto = getUserById(userId);

        userRepository.deleteById(userId);
        log.info("USER WITH ID: " + deletedUserDto.getId() + " WAS DELETED.");

    }

}
