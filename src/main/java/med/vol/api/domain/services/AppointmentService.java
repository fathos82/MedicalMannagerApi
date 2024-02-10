package med.vol.api.domain.services;

import jakarta.validation.ValidationException;
import med.vol.api.domain.dtos.request.AppointmentRequest;
import med.vol.api.domain.entities.Appointment;
import med.vol.api.domain.entities.Doctor;
import med.vol.api.domain.entities.Patient;
import med.vol.api.domain.repositories.AppointmentRepository;
import med.vol.api.domain.repositories.DoctorRepository;
import med.vol.api.domain.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    public void registerAppointment(AppointmentRequest data) {
        if (data.doctorId()!= null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationException("Patient not found");
        }
        if (!patientRepository.existsById(data.patientId())) {
            throw new ValidationException("Patient not found");
        }


        Doctor doctor = chooseDoctor(data);
        Patient patient = patientRepository.findById(data.patientId()).orElseThrow();

      //  appointmentRepository.save(new Appointment(doctor, patient, data.date()))

    }

    private Doctor chooseDoctor(AppointmentRequest data) {
        if (data.doctorId() != null) {
            return doctorRepository.findById(data.doctorId()).orElseThrow();
        }
        if (data.specialty() != null) {
            return doctorRepository.findAvailableBySpecialtyAndActiveTrue(data.specialty());
        }
        throw new ValidationException("Doctor not found");

    }



}
