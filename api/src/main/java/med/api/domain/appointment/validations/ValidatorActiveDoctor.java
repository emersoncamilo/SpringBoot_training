package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentScheduleData;
import med.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidatorActiveDoctor implements ValidatorAppointmentScheduling{
    @Autowired
    private DoctorRepository doctorRepository;


    public void validate(AppointmentScheduleData data){
        // choose doctor is optional
        if (data.idDoctor() == null ){
            return;
        }
        var isDoctorActive = doctorRepository.findActiveById(data.idDoctor());
        if (!isDoctorActive){
            throw new ValidationException("Appointment can't be scheduled with excluded doctor");
        }
    }

}
