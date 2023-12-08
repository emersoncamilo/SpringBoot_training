package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentRepository;
import med.api.domain.appointment.AppointmentScheduleData;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorPatientWithoutAnotherAppointmentOnDay {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleData data){
        var firstTimetable = data.date().withHour(7);
        var lastTimetable = data.date().withHour(18);
        var patientHasAnotherAppointmentOnDay = appointmentRepository.existsByPatientIdAndDateBetween(data.idPatient(), firstTimetable, lastTimetable);
        if (patientHasAnotherAppointmentOnDay) {
            throw new ValidationException("Patient already has an appointment scheduled that day");
        }
    }


}
