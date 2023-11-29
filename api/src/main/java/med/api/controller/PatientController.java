package med.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.api.domain.patient.Patient;
import med.api.domain.patient.PatientDetailsData;
import med.api.domain.patient.PatientRegistrationData;
import med.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
public class PatientController {


    @Autowired
    private PatientRepository repository;


    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder) {
        System.out.println("Patient SAVE is ready");
        //saving patient
        var patient = new Patient(data);
        repository.save(patient);

        // creating body request
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        //sendind the body request
        return ResponseEntity.created(uri).body(new PatientDetailsData(patient));
    }

    @GetMapping
    public void allPatient() {
        System.out.println("Get Patient is ready");
    }
}