package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentRepository;
import med.api.domain.appointment.AppointmentScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorWithAnotherAppointmentSameTime implements ValidatorAppointmentScheduling{

    @Autowired
    private AppointmentRepository appointmentRepository;



    public void validate(AppointmentScheduleData data){
        var doctorHasAnotherConsultationAtTheSameTime = appointmentRepository.existsByDoctorIdAndAppointmentDate(data.idDoctor(), data.appointmentDate());
        if (doctorHasAnotherConsultationAtTheSameTime) {
            throw new ValidationException("The doctor already has another appointment scheduled at the same time.");
        }
    }
}