package med.vol.api.dtos.response;

import med.vol.api.dtos.AddressDto;
import med.vol.api.dtos.SpecializationTypes;
import med.vol.api.entities.Doctor;

public record DoctorStatusResponse(
        Long id,
        String name,
        String phone,
        String email,
        String crm,
        AddressDto address, SpecializationTypes specialization

) {
    public DoctorStatusResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getPhone(), doctor.getEmail(), doctor.getCrm(), new AddressDto(doctor.getAddress()), doctor.getSpecialty());
    }
}
