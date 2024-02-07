package ru.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.registration.model.Patient;

/**
 * @author MR.k0F31n
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
