package med.vol.api.dtos;

import med.vol.api.entities.Address;

public record AddressDto(String street, String number, String complement) {
    public AddressDto(Address address) {
        this(address.getStreet(), address.getNumber(), address.getComplement());
    }
}