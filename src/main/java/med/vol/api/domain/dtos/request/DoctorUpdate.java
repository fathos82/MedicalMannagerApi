package med.vol.api.domain.dtos.request;

import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.dtos.AddressDto;

public record DoctorUpdate(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDto address) {

}
