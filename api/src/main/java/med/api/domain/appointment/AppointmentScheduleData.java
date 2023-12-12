package med.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentScheduleData(
        Long idDoctor,
        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime appointmentDate,
        Specialty specialty) {
}
