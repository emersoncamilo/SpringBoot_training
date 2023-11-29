package med.api.domain.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.api.domain.address.Address;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;
    private String phone;
    private String idDoctor;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    
    @Embedded
    private Address address;

    private Boolean active;



    public Doctor(DoctorRegistrationData data) {
        this.active = true;
        this.fullName = data.fullName();
        this.email = data.email();
        this.phone = data.phone();
        this.idDoctor = data.idDoctor();
        this.specialty = data.specialty();
        this.address = new Address(data.addressData());
    }

    public void updateInfo(DoctorUpdateData dataDoctor) {
        if (dataDoctor.fullName() != null) {
            this.fullName = dataDoctor.fullName();
        }

        if (dataDoctor.phone() != null) {
            this.phone = dataDoctor.phone();
        }
        if (dataDoctor.address() != null) {
            this.address.updateInfo(dataDoctor.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}