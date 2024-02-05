package med.vol.api.dtos.request;

import jakarta.validation.constraints.NotNull;
import med.vol.api.dtos.AddressDto;

public record DoctorUpdate(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDto address) {

}
