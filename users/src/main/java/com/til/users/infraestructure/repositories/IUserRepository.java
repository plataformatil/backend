package com.til.users.infraestructure.repositories;

import com.til.users.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.name = :userNameOrEmail OR u.email = :userNameOrEmail")
    Optional<User> findByUserOrEmail(@Param("userNameOrEmail") String userNameOrEmail);
}