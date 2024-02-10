package med.vol.api.domain.dtos.response;

import med.vol.api.domain.entities.Patient;

public record PatientResponse(Long id, String name, String email, String cpf) {
    public PatientResponse(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }

}
