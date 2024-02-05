package med.vol.api.controllers;

import jakarta.validation.Valid;
import med.vol.api.dtos.request.DoctorRequest;
import med.vol.api.dtos.response.DoctorResponse;
import med.vol.api.dtos.request.DoctorUpdate;
import med.vol.api.dtos.response.DoctorStatusResponse;
import med.vol.api.entities.Doctor;
import med.vol.api.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerDoctor(@RequestBody @Valid DoctorRequest data, UriComponentsBuilder uriBuilder) {
        Doctor doctor = new Doctor(data);
        doctorRepository.save(doctor);
        URI uri  = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorStatusResponse(doctor));
    }
    @GetMapping
    public ResponseEntity<Page<DoctorResponse>> getAllDoctors(@PageableDefault(size = 10, sort = {"specialty"},direction = Sort.Direction.DESC) Pageable pageable){
        Page page = doctorRepository.findAllByActiveTrue(pageable).map(DoctorResponse::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdate data) {
        Doctor doctor = doctorRepository.findById(data.id()).orElseThrow();
        doctor.update(data);
        return ResponseEntity.ok(new DoctorStatusResponse(doctor));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.desactivate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    @Transactional
    public ResponseEntity getDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(new DoctorStatusResponse(doctor));
    }



}
