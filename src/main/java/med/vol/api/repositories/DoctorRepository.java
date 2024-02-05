package med.vol.api.repositories;

import med.vol.api.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Override
    <S extends Doctor> S save(S entity);
    Page<Doctor> findAllByActiveTrue(Pageable pageable);




}
