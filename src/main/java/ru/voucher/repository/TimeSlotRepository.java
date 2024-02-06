package ru.voucher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voucher.model.TimeSlot;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author MR.k0F31n
 */
@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    Optional<TimeSlot> findAllByDoctorIdAndDateAndPatientIdIsNull(Long idDoctor, LocalDate date);

    Optional<TimeSlot> findAllByPatientId(Long id);
}
