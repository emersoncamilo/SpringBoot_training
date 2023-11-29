package med.api.domain.doctor;

import med.api.domain.address.Address;



public record DoctorDetailsData(
    Long id, 
    String fullName,
    String email,
    String idDoctor,
    String phone,
    Specialty specialty,
    Address address
    ){

    public DoctorDetailsData (Doctor doctor){
        this(   doctor.getId(), 
                doctor.getFullName(), 
                doctor.getEmail(), 
                doctor.getIdDoctor(), 
                doctor.getPhone(), 
                doctor.getSpecialty(), 
                doctor.getAddress() 
            );


    }    
}
