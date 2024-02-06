package ru.voucher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voucher.model.Patient;

/**
 * @author MR.k0F31n
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
