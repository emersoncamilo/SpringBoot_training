package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentScheduleData;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidatorAdvanceTime {

    public void validate(AppointmentScheduleData data){
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(appointmentDate, now).toMinutes();
        if (differenceInMinutes < 30){
            throw new ValidationException("Appointment must be scheduled 30 minutes in advance");
        }
    }
}