package med.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.api.domain.address.AddressData;

public record PatientRegistrationData(
    @NotBlank
    String fullName,
    @NotBlank
    @Email
    String email,
    @NotBlank
    @Pattern(regexp = "\\d{5,15}")
    String document,
    @NotBlank
    String phone,
    @NotNull
    @Valid
    AddressData addressData) {
}