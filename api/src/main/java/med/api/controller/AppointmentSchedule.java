package med.api.controller;

import med.api.domain.ValidationException;
import med.api.domain.appointment.Appointment;
import med.api.domain.appointment.AppointmentRepository;
import med.api.domain.appointment.AppointmentScheduleData;
import med.api.domain.doctor.Doctor;
import med.api.domain.doctor.DoctorRepository;
import med.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void toSchedule (AppointmentScheduleData data){
        if (data.idDoctor() !=null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidationException("Doctor id does not exist!");
        }

        if (!patientRepository.existsById(data.idPatient())){
            throw new ValidationException("Patient id does not exist!");
        }
//        var patient = patientRepository.findById(data.idPatient()).get();
//        var doctor = chooseDoctor(data);
//        var appointment = new Appointment(null, doctor, patient, data.date());
//        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentScheduleData data) {
        if (data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        if (data.specialty() == null){
            throw new ValidationException("Speciality is obligated when doctor wasn't chosen");
        }
        return doctorRepository.chooseDoctorFreeOnDate(data.specialty(), data.date());


//        doctorRepository.findById(data.idDoctor()).get()
    }

}
