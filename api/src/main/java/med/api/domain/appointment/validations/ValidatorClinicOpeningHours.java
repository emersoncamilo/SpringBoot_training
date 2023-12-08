package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentScheduleData;

import java.time.DayOfWeek;

public class ValidatorClinicOpeningHours {

    public void validate(AppointmentScheduleData data){

        var appointmentDate = data.date();

        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpen = appointmentDate.getHour() < 7;
        var afterClinicClose = appointmentDate.getHour() > 18;

        if (sunday || beforeClinicOpen || afterClinicClose){
            throw new ValidationException("Outside clinic opening hours");
        }
    }
}
