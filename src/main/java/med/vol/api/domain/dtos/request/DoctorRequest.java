package med.vol.api.domain.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import med.vol.api.domain.dtos.AddressDto;
import med.vol.api.domain.enums.DoctorSpecializationTypes;

public record DoctorRequest(
        @Size(min = 3, max = 100)
        String name,
        @Email
        String email,

        String phone,
        String crm,
        DoctorSpecializationTypes specialty,
        AddressDto address,
        @Size(min = 11, max = 11)
        String cpf) {
}
