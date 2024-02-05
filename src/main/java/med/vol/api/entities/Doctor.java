package med.vol.api.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import med.vol.api.dtos.DoctorRequestDto;
import med.vol.api.dtos.DoctorUpdateDto;
import med.vol.api.dtos.SpecializationTypes;
import org.springframework.web.bind.annotation.RequestBody;




@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private SpecializationTypes specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(@RequestBody @Valid DoctorRequestDto requestDoctor) {
        this.name = requestDoctor.name();
        this.email = requestDoctor.email();
        this.phone = requestDoctor.phone();
        this.crm = requestDoctor.crm();
        this.specialty = requestDoctor.specialty();
        this.address = new Address(requestDoctor.address());
        this.cpf = requestDoctor.cpf();
        this.active = true;
    }

    public void update(@RequestBody @Valid DoctorUpdateDto requestDoctor) {
        if (requestDoctor.name() != null) {
            this.name = requestDoctor.name();
        }
        if (requestDoctor.address() != null) {
            this.address.update(requestDoctor.address());
        }
        if (requestDoctor.phone() != null) {
            this.phone = requestDoctor.phone();
        }
    }
    public void desactivate(){
        this.active = false;
    }

    public String toString() {
        return "Doctor(name=" + this.name + ", email=" + this.email + ", phone=" + this.phone + ", crm=" + this.crm + ", specialty=" + this.specialty + ", address=" + this.address +", cpf="+this.cpf+ ")";
    }

}
