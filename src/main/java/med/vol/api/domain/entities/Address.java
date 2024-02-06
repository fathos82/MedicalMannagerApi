package med.vol.api.domain.entities;

import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.vol.api.domain.dtos.AddressDto;
import org.springframework.web.bind.annotation.RequestBody;
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String number;
    private String complement;

    public Address(@RequestBody @Valid AddressDto requestAddress) {
        this.street = requestAddress.street();
        this.number = requestAddress.number();
        this.complement = requestAddress.complement();
    }

    public void update(@RequestBody @Valid AddressDto requestAddress) {
        if (requestAddress.street() != null) {
            this.street = requestAddress.street();
        }
        if (requestAddress.number() != null) {
            this.number = requestAddress.number();
        }
        if (requestAddress.complement() != null) {
            this.complement = requestAddress.complement();
        }
    }

    public String toString() {
        return "Andress(street=" + this.street + ", number=" + this.number + ", complement=" + this.complement + ")";
    }

}
