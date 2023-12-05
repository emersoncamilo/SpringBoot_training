package med.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.api.domain.appointment.AppointmentDetailData;
import med.api.domain.appointment.AppointmentScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule (@RequestBody @Valid AppointmentScheduleData data){
        schedule.toSchedule(data);
        System.out.println(data);
        return ResponseEntity.ok(new AppointmentDetailData(null, null, null, null));
    }
}