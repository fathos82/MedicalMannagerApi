package med.vol.api.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record DoctorRequestDto(
        @Size(min = 3, max = 100)
        String name,
        @Email
        String email,

        String phone,
        String crm,
        SpecializationTypes specialty,
        AddressDto address,
        @Size(min = 11, max = 11)
        String cpf) {
}
