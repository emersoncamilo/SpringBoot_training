package med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.api.domain.doctor.Doctor;
import med.api.domain.doctor.DoctorDetailsData;
import med.api.domain.doctor.DoctorListingData;
import med.api.domain.doctor.DoctorRegistrationData;
import med.api.domain.doctor.DoctorRepository;
import med.api.domain.doctor.DoctorUpdateData;


@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity save(@RequestBody @Valid DoctorRegistrationData data, UriComponentsBuilder uriBuilder){
        
        var doctor = new Doctor(data);
        repository.save(doctor);
        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new DoctorDetailsData(doctor));
    }

    @GetMapping
    public ResponseEntity <Page<DoctorListingData>> list (@PageableDefault(size = 5, sort = {"fullName"})
                                                              Pageable pagination){
        var page = repository.findAllByActiveTrue(pagination).map(DoctorListingData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateInfo(@RequestBody @Valid DoctorUpdateData doctorUpdate){
        var doctor = repository.getReferenceById(doctorUpdate.id());
        doctor.updateInfo(doctorUpdate);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity details(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailsData(doctor));
    }

}