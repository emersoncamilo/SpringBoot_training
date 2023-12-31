package med.api.domain.doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.api.domain.address.AddressData;

public record DoctorRegistrationData(
    
    @NotBlank
    String fullName,
    
    @NotBlank
    @Email
    String email,
    
    @NotBlank
    String phone,
    
    @NotBlank
    @Pattern(regexp="\\d{4,6}")
    String idDoctor,

    @NotNull
    Specialty specialty,
    
    @NotNull
    @Valid
    AddressData addressData
    ){
}
