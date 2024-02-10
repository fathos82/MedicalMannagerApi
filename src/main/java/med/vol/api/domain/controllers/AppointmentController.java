package med.vol.api.domain.controllers;

import jakarta.validation.Valid;
import med.vol.api.domain.dtos.request.AppointmentRequest;
import med.vol.api.domain.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@Controller

public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @PostMapping("/appointment")
    @Transactional
    public ResponseEntity registerAppointment(@RequestBody @Valid AppointmentRequest data) {
        appointmentService.registerAppointment(data);
        return null;
    }
}
