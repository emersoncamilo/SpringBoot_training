package med.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndAppointmentDate(Long aLong, LocalDateTime appointmentDate);

    boolean existsByPatientIdAndAppointmentDateBetween(Long idPatient, LocalDateTime firstTimetable, LocalDateTime lastTimetable);

}
