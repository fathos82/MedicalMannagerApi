package med.vol.api.domain.dtos.response;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.entities.Appointment;

import java.time.LocalDateTime;

public record AppointmentResponse(
        @NotNull
        Long id,

        @NotNull
        @Future
        LocalDateTime date
) {

        public AppointmentResponse(Appointment appointmentRegistered) {
                this(appointmentRegistered.getId(), appointmentRegistered.getDate());
        }
}
