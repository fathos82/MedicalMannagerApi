package med.vol.api.domain.dtos.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import med.vol.api.domain.enums.CancellationReasonTypes;

public record AppointmentCancelInfoRequest(
        Long id,
        @Enumerated(EnumType.STRING)
        CancellationReasonTypes cancellationReason
) {
}
