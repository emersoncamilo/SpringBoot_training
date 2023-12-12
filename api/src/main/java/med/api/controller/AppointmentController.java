package med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.api.domain.appointment.AppointmentSchedule;
import med.api.domain.appointment.AppointmentScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity toSchedule (@RequestBody @Valid AppointmentScheduleData data){
        var dto = appointmentSchedule.toSchedule(data);
        return ResponseEntity.ok(dto);
    }
}