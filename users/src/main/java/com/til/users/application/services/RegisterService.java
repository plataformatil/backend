package com.til.users.application.services;

import com.til.users.domain.entities.Address;
import com.til.users.domain.entities.User;
import com.til.users.domain.exceptions.InvalidUserException;
import com.til.users.domain.validations.UserValidation;
import com.til.users.infraestructure.repositories.IAddressRepository;
import com.til.users.infraestructure.repositories.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private IUserRepository userRepository;
    private IAddressRepository addressRepository;
    private BCryptPasswordEncoder passEncoder;

    public RegisterService(IUserRepository userRepository, IAddressRepository addressRepository, BCryptPasswordEncoder passEncoder) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passEncoder = passEncoder;
    }

    public String createUser(User user) throws InvalidUserException {
        UserValidation.isValid(user);
        Address dataAddress = createAddress(user.address);

        user.password = passEncoder.encode(user.password);
        user.address = dataAddress;
        userRepository.save(user);

        return "Usuário criado com sucesso";
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
