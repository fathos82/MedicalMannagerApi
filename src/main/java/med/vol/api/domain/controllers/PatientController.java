package med.vol.api.domain.controllers;

import jakarta.validation.Valid;
import med.vol.api.domain.dtos.request.PatientRequest;
import med.vol.api.domain.dtos.response.PatientResponse;
import med.vol.api.domain.entities.Patient;
import med.vol.api.domain.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping("/patient")
    @Transactional
    public ResponseEntity<PatientResponse> registerPatient(@RequestBody @Valid PatientRequest data, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(data);
        patientRepository.save(patient);
        URI uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientResponse(patient));
    }

    @DeleteMapping("/patient/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow();
        patient.deactivate();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/patients")
    @Transactional
    public ResponseEntity<Page<PatientResponse>> getPatients(Pageable pageable) {
        Page<PatientResponse> page = patientRepository.findAllByActiveTrue(pageable).map(PatientResponse::new);
        return ResponseEntity.ok(page);

    }
}
