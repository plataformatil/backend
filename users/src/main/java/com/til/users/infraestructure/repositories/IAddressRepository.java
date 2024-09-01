package com.til.users.infraestructure.repositories;

import com.til.users.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAddressRepository extends JpaRepository<Address, UUID> {
}
