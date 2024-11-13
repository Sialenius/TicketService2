package com.project.jfb.repository;


import com.project.jfb.io.entity.UserEntity;
import com.project.jfb.io.entity.enums.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {
    UserEntity findByName(String userName);


    @Modifying
    @Query(value="UPDATE UserEntity u SET u.id = :newId WHERE u.id = :id")
    void updateUserId(@Param("id") UUID id, @Param("newId") UUID newId);

    @Modifying
    @Query("DELETE UserEntity u WHERE u.id = :id")
    void deleteUser(@Param("id") UUID id);


}