package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentRepository;
import med.api.domain.appointment.AppointmentScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientWithoutAnotherAppointmentOnDay implements ValidatorAppointmentScheduling{

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentScheduleData data){
        var firstTimetable = data.appointmentDate().withHour(7);
        var lastTimetable = data.appointmentDate().withHour(18);
        var patientHasAnotherAppointmentOnDay = appointmentRepository.existsByPatientIdAndAppointmentDateBetween(data.idPatient(), firstTimetable, lastTimetable);
        if (patientHasAnotherAppointmentOnDay) {
            throw new ValidationException("Patient already has an appointment scheduled that day");
        }
    }


}
