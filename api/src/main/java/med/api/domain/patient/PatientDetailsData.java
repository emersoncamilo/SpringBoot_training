package med.api.domain.patient;

import med.api.domain.address.Address;

public record PatientDetailsData(Long id, String fullName, String email, String document, String phone, Address address) {
    public PatientDetailsData(Patient patient) {
        this(patient.getId(), patient.getFullName(), patient.getEmail(), patient.getDocument(), patient.getPhone(), patient.getAddress());
    }
}
