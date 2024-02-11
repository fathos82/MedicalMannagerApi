package med.vol.api.domain.services;

import jakarta.validation.ValidationException;
import med.vol.api.domain.dtos.request.AppointmentCancelInfoRequest;
import med.vol.api.domain.dtos.request.AppointmentRequest;
import med.vol.api.domain.entities.Appointment;
import med.vol.api.domain.entities.Doctor;
import med.vol.api.domain.entities.Patient;
import med.vol.api.domain.repositories.AppointmentRepository;
import med.vol.api.domain.repositories.DoctorRepository;
import med.vol.api.domain.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    public Appointment registerAppointment(AppointmentRequest data) {
        if (data.doctorId()!= null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidationException("Patient not found");
        }
        if (!patientRepository.existsById(data.patientId())) {
            throw new ValidationException("Patient not found");
        }


        Doctor doctor = chooseDoctor(data);
        Patient patient = patientRepository.findById(data.patientId()).orElseThrow();
        Appointment appointment = new Appointment( doctor, patient, data.date());
        appointmentRepository.save(appointment);
        return appointment;
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


    public void cancel(AppointmentCancelInfoRequest data) {
        Appointment appointment = appointmentRepository.findById(data.id())
                .orElseThrow(() -> new ValidationException("The appointment with id " + data.id()+ " was not found"));
        validateCancellationTime(appointment);
        appointment.cancel(data.cancellationReason());

    }

    private void validateCancellationTime(Appointment appointment) {
        if (timeIsExceeded(appointment)) {
            throw new ValidationException("The cancellation time for the appointment has been exceeded");
        }
    }
    private boolean timeIsExceeded(Appointment appointment) {
        LocalDateTime date = appointment.getDate();
        LocalDateTime current = LocalDateTime.now();
        long diff = Duration.between(date, current).toDays();
        return diff >= 1;
    }
}
