package med.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.api.domain.address.AddressData;

public record DoctorUpdateData(
    
    @NotNull
    Long id,

    String fullName,
    
    String phone,
    
    AddressData addressData) {

        // public DoctorUpdateData(Doctor doctor){
        //     this(doctor.getId(), doctor.getFullName(), doctor.getPhone(), doctor.getAddress())
        // }

    
}
