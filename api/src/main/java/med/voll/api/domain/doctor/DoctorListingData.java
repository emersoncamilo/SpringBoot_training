package med.voll.api.domain.doctor;

public record DoctorListingData(
    Long id,
    String fullName,
    String email,
    String idDoctor,
    Specialty specialty
    ){


        public DoctorListingData (Doctor doctor){
            this(doctor.getId(), doctor.getFullName(), doctor.getEmail(), doctor.getIdDoctor(), doctor.getSpecialty());
        }



}
