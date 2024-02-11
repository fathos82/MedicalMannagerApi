package med.vol.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.dtos.request.AppointmentRequest;
import med.vol.api.domain.enums.CancellationReasonTypes;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    LocalDateTime date;

    @Column(name = "cancellation_reason")
    @Enumerated(EnumType.STRING)
    CancellationReasonTypes cancellationReason;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime date) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.cancellationReason = null;
    }



    public void cancel(CancellationReasonTypes cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
