package com.til.users.domain.validations;

import com.til.users.domain.entities.Address;
import com.til.users.domain.exceptions.InvalidAddressException;

import java.util.regex.Pattern;

public class AddressValidation {
    //Regex do CEP\\ - mr.p
    private static final Pattern CEP_PATTERN = Pattern.compile("\\d{5}-\\d{3}");

    public static boolean isValid(Address address) throws InvalidAddressException {
        if (address.street == null || address.street.isEmpty()) {
            throw new InvalidAddressException("O nome da rua é nulo ou vazio");
        }
        if (address.city == null || address.city.isEmpty()) {
            throw new InvalidAddressException("O nome da cidade é nulo ou vazio");
        }
        if (address.state == null || address.state.isEmpty()) {
            throw new InvalidAddressException("O nome do estado é nulo ou vazio");
        }
        if (address.country == null || address.country.isEmpty()) {
            throw new InvalidAddressException("O nome do país é nulo ou vazio");
        }
        if (address.cep == null || !CEP_PATTERN.matcher(address.cep).matches()) {
            throw new InvalidAddressException("O CEP é nulo ou não está no formato adequado");
        }
        return true;
    }
}
