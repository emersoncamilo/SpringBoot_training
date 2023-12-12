package med.api.domain.appointment;

import med.api.domain.ValidationException;
import med.api.domain.appointment.validations.ValidatorAppointmentScheduling;
import med.api.domain.doctor.Doctor;
import med.api.domain.doctor.DoctorRepository;
import med.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;


    @Autowired
    private List<ValidatorAppointmentScheduling> validators;

    public AppointmentDetailData toSchedule (AppointmentScheduleData data){
        if (!patientRepository.existsById(data.idPatient())){
            throw new ValidationException("Patient id does not exist!");
        }
        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationException("Doctor id does not exist!");
        }
        validators.forEach(v -> v.validate(data));
        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = chooseDoctor(data);

        if (doctor == null) {
            throw new ValidationException("No doctor available on this appointment Date!.");
        }
        var appointment = new Appointment(null, doctor, patient, data.appointmentDate());
        appointmentRepository.save(appointment);

        return new AppointmentDetailData(appointment);
    }

    private Doctor chooseDoctor(AppointmentScheduleData data) {
        if (data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.specialty() == null){
            throw new ValidationException("Speciality is obligated when doctor wasn't chosen");
        }
        return doctorRepository.chooseDoctorFreeOnDate(data.specialty(), data.appointmentDate());
    }
}