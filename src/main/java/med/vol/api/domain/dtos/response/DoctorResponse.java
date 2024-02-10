package med.vol.api.domain.dtos.response;

import med.vol.api.domain.dtos.DoctorSpecializationTypes;
import med.vol.api.domain.entities.Doctor;

public record DoctorResponse(Long id, String name, String email, String phone, DoctorSpecializationTypes specialization, boolean active) {
    public DoctorResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getSpecialty(), doctor.getActive());
    }
}
