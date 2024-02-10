package med.vol.api.domain.repositories;

import lombok.NonNull;
import med.vol.api.domain.dtos.DoctorSpecializationTypes;
import med.vol.api.domain.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Override
    @NonNull
    <S extends Doctor> S save(@NonNull S entity);
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            SELECT d FROM Doctor d 
            WHERE d.specialty = :specialty
            and 
            d.active = true
            and
            d.id not in(
            SELECT a.doctor.id FROM Appointment a
            WHERE a.date = :date
            )
            """)

    Doctor findAvailableBySpecialtyAndActiveTrue(DoctorSpecializationTypes specialty);
}
