package com.til.users.application.services;

import com.til.users.infraestructure.repositories.IAddressRepository;
import com.til.users.infraestructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisterService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IAddressRepository addressRepository;
}
