package med.api.domain.doctor;

import jakarta.persistence.*;
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

    @Column(name = "active")
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

    public void updateInfo(DoctorUpdateData dataUpdate) {
        if (dataUpdate.fullName() != null) {
            this.fullName = dataUpdate.fullName();
        }

        if (dataUpdate.phone() != null) {
            this.phone = dataUpdate.phone();
        }
        if (dataUpdate.addressData() != null) {
            this.address.updateInfo(dataUpdate.addressData());
        }
    }

    public void delete() {
        this.active = false;
    }
}