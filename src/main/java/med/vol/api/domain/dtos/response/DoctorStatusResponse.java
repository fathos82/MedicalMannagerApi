package med.vol.api.domain.dtos.response;

import med.vol.api.domain.dtos.AddressDto;
import med.vol.api.domain.enums.DoctorSpecializationTypes;
import med.vol.api.domain.entities.Doctor;

public record DoctorStatusResponse(
        Long id,
        String name,
        String phone,
        String email,
        String crm,
        AddressDto address, DoctorSpecializationTypes specialization

) {
    public DoctorStatusResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getPhone(), doctor.getEmail(), doctor.getCrm(), new AddressDto(doctor.getAddress()), doctor.getSpecialty());
    }
}
