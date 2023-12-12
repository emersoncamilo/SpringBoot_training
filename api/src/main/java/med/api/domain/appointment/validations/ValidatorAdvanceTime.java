package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentScheduleData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ValidatorAdvanceTime implements ValidatorAppointmentScheduling{

    public void validate(AppointmentScheduleData data){
        var appointmentDate = data.appointmentDate();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();
        if (differenceInMinutes < 30){
            throw new ValidationException("Appointment must be scheduled 30 minutes in advance");
        }
    }
}