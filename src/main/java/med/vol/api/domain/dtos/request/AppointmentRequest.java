package med.vol.api.domain.dtos.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.enums.DoctorSpecializationTypes;

import java.time.LocalDateTime;

public record AppointmentRequest(
        @NotNull
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        DoctorSpecializationTypes specialty
){
}
