package med.vol.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.dtos.request.PatientRequest;


@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private boolean active;

    public void deactivate() {
        this.active = false;
    }

    public Patient(PatientRequest data) {
        this.name = data.name();
        this.email = data.email();
        this.cpf = data.cpf();
        this.active = true;
    }

}
