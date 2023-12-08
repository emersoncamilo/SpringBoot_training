package med.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndData(Long aLong, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTimetable, LocalDateTime lastTimetable);

}
