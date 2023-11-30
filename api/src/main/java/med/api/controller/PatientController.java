package med.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.api.domain.patient.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

        //saving patient
        var patient = new Patient(data);
        repository.save(patient);

        // creating body request
        var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();

        //sendind the body request
        return ResponseEntity.created(uri).body(new PatientDetailsData(patient));
    }

    @GetMapping
    public ResponseEntity <Page<PatientListingData>> list(@PageableDefault(size =5, sort = {"fullName"}) Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(PatientListingData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailsData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateInfo(@RequestBody @Valid PatientUpdateData patientUpdate){
        var patient = repository.getReferenceById(patientUpdate.id());
        patient.updateInfo(patientUpdate);
        return ResponseEntity.ok(new PatientDetailsData(patient));
    }
}