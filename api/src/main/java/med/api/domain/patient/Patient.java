package med.api.domain.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.api.domain.address.Address;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String document;

    private String phone;

    @Embedded
    private Address address;


    private Boolean active;

    public Patient(PatientRegistrationData data) {
        this.active = true;
        this.fullName = data.fullName();
        this.email = data.email();
        this.phone = data.phone();
        this.document = data.document();
        this.address = new Address(data.addressData());
    }


    public void updateInfo(PatientUpdateData patientUpdate) {

        if (patientUpdate.fullName() != null) {
            this.fullName = patientUpdate.fullName();
        }
        if (patientUpdate.phone() != null) {
            this.phone = patientUpdate.phone();
        }
        if (patientUpdate.addressData() != null) {
            this.address.updateInfo(patientUpdate.addressData());
        }
    }
}