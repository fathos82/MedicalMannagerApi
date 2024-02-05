package med.vol.api.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import med.vol.api.dtos.AddressDto;
import med.vol.api.dtos.SpecializationTypes;

public record DoctorRequest(
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
