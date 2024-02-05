package med.vol.api.dtos.response;

import med.vol.api.dtos.SpecializationTypes;
import med.vol.api.entities.Doctor;

public record DoctorResponse(Long id, String name, String email, String phone, SpecializationTypes specialization, boolean active) {
    public DoctorResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getSpecialty(), doctor.getActive());
    }
}
