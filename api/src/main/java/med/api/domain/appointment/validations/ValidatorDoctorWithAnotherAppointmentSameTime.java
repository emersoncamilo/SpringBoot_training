package med.api.domain.appointment.validations;

import med.api.domain.ValidationException;
import med.api.domain.appointment.AppointmentRepository;
import med.api.domain.appointment.AppointmentScheduleData;
import med.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidatorDoctorWithAnotherAppointmentSameTime {

    @Autowired
    private AppointmentRepository appointmentRepository;



    public void validate(AppointmentScheduleData data){
        var doctorHasAnotherConsultationAtTheSameTime = appointmentRepository.existsByDoctorIdAndData(data.idDoctor(), data.date());
        if (doctorHasAnotherConsultationAtTheSameTime) {
            throw new ValidationException("The doctor already has another appointment scheduled at the same time.");
        }
    }
}