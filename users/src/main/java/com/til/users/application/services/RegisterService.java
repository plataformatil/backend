package com.til.users.application.services;

import com.til.users.application.models.RegisterModel;
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
    private final IUserRepository userRepository;
    private final IAddressRepository addressRepository;
    private final BCryptPasswordEncoder passEncoder;
    private final RegisterAttemptService registerAttemptService;

    public RegisterService(IUserRepository userRepository, IAddressRepository addressRepository,
                           BCryptPasswordEncoder passEncoder, RegisterAttemptService registerAttemptService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passEncoder = passEncoder;
        this.registerAttemptService = registerAttemptService;
    }

    public boolean createUser(RegisterModel register, String ip) throws InvalidUserException, Exception {
        if (registerAttemptService.isBlocked(ip)) {
            throw new Exception(
                    "IP temporariamente bloqueado devido a múltiplas " +
                            " de cadastro falhadas");
        }
        try {
            User user = new User(register);
            UserValidation.isValid(user, userRepository);

            Address dataAddress = createAddress(user.getAddress());
            user.setPassword(passEncoder.encode(user.getPassword()));
            user.setAddress(dataAddress);
            userRepository.save(user);
            registerAttemptService.registrationSucceeded(ip);
            return true;
        } catch (InvalidUserException ex) {
            registerAttemptService.registrationFailed(ip);
            throw new InvalidUserException(ex.getMessage());
        }
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }
}
