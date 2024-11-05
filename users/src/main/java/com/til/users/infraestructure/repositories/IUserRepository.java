package com.til.users.infraestructure.repositories;

import com.til.users.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.name = :userNameOrEmail OR u.email = :userNameOrEmail")
    Optional<User> findByUserOrEmail(@Param("userNameOrEmail") String userNameOrEmail);

    @Query("SELECT u.cpf FROM User u WHERE u.cpf IS NOT Null")
    List<String> findAllUsersCpf();

    @Query("SELECT u.cnpj FROM User u WHERE u.cnpj IS NOT Null")
    List<String> findAllUsersCnpj();
}