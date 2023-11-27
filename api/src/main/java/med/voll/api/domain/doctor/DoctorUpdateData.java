package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressData;

public record DoctorUpdateData(
    
    @NotNull
    Long id,

    String fullName,
    
    String phone,
    
    AddressData address) {

        // public DoctorUpdateData(Doctor doctor){
        //     this(doctor.getId(), doctor.getFullName(), doctor.getPhone(), doctor.getAddress())
        // }

    
}
