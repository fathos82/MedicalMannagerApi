package med.vol.api.dtos;

import med.vol.api.entities.Doctor;

public record DoctorResponseDto(Long id, String name, String email, String phone, SpecializationTypes specialization, boolean active) {
    public DoctorResponseDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getSpecialty(), doctor.getActive());
    }
}
