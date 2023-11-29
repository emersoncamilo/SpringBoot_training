package med.api.domain.address;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AddressData(
    
    @NotNull    
    String publicPlace,
    
    @NotNull
    @Pattern(regexp="\\d{8}")
    String zipCode,
    
    @NotNull
    String district,
    
    @NotNull
    String city,
    
    @NotNull
    String numberAddress,

    String complement) {

}
