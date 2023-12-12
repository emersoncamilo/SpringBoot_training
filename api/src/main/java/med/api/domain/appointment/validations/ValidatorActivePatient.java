package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentScheduleData;
import med.api.domain.doctor.DoctorRepository;
import med.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActivePatient implements ValidatorAppointmentScheduling{

    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentScheduleData data){
        var isPatientActive = patientRepository.findActiveById(data.idPatient());
        if (!isPatientActive){
            throw new ValidationException("Appointment can't be scheduled with excluded Patient");
        }
    }



}
