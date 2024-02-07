package ru.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.registration.model.Doctor;

/**
 * @author MR.k0F31n
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
