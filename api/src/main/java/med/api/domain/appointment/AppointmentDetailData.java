package med.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailData(
        Long id,
        Long idDoctor,
        Long idPacient,
        LocalDateTime date) {
}
