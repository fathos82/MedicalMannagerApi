package med.vol.api.domain.dtos;

import med.vol.api.domain.entities.Address;

public record AddressDto(String street, String number, String complement) {
    public AddressDto(Address address) {
        this(address.getStreet(), address.getNumber(), address.getComplement());
    }
}