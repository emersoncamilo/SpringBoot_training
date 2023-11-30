package med.api.domain.patient;

public record PatientListingData(
        Long id,
        String fullName,
        String email,
        String document,
        String phone
        ) {
    public PatientListingData(Patient patient) {
        this (patient.getId(), patient.getFullName(), patient.getEmail(), patient.getDocument(), patient.getPhone());
    }
}