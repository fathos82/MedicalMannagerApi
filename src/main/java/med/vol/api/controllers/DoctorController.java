package med.vol.api.controllers;

import jakarta.validation.Valid;
import med.vol.api.dtos.DoctorRequestDto;
import med.vol.api.dtos.DoctorResponseDto;
import med.vol.api.dtos.DoctorUpdateDto;
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

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerDoctor(@RequestBody @Valid DoctorRequestDto data) {
        Doctor doctor = new Doctor(data);
        System.out.println(doctor);
        doctorRepository.save(doctor);

        return ResponseEntity.ok().build();
    }
    @GetMapping
    public Page<DoctorResponseDto> getAllDoctors(@PageableDefault(size = 10, sort = {"specialty"},direction = Sort.Direction.DESC) Pageable pageable){
        return doctorRepository.findAllByActiveTrue(pageable).map(DoctorResponseDto::new);

    }


    @PutMapping
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorUpdateDto data) {
        Doctor doctor = doctorRepository.findById(data.id()).orElseThrow();
        doctor.update(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow();
        doctor.desactivate();
        return ResponseEntity.ok().build();
    }



}
