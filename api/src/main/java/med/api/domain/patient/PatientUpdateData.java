package med.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.api.domain.address.AddressData;

public record PatientUpdateData(
        @NotNull
        Long id,

        String fullName,

        String phone,

        AddressData addressData
) {

}
