package ru.voucher.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voucher.dto.TimeSlotInfo;
import ru.voucher.exeption.NotFoundException;
import ru.voucher.exeption.ValidatorException;
import ru.voucher.mapper.TimeSlotMapper;
import ru.voucher.model.Doctor;
import ru.voucher.model.Patient;
import ru.voucher.model.TimeSlot;
import ru.voucher.repository.DoctorRepository;
import ru.voucher.repository.PatientRepository;
import ru.voucher.repository.TimeSlotRepository;
import ru.voucher.service.TimeSlotService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static ru.voucher.mapper.TimeSlotMapper.toDto;

/**
 * @author MR.k0F31n
 */
@Service
@RequiredArgsConstructor
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    public TimeSlotInfo registerPatient(Long idTimeSlot, Long idPatient) {
        TimeSlot timeSlot = timeSlotRepository.findById(idTimeSlot).orElseThrow(
                () -> new NotFoundException("Talon of patient not found!")
        );
        if (timeSlot.getPatient() == null) {
            Long id = idPatient;
            Patient patient = patientRepository.findById(id).orElseThrow(
                    () -> new NotFoundException("Talon of patient not found!")
            );
            timeSlot.setPatient(patient);
            timeSlotRepository.save(timeSlot);
            return toDto(timeSlot);
        } else {
            //Не хочется лишний раз напрягать систему исключениями, обработает фронт
            return null;
        }
    }

    @Override
    public List<TimeSlotInfo> findAllTimeSlotFromPatient(Long idPatient) {
        final List<TimeSlot> allTalonFromPatient = timeSlotRepository.findAllByPatientId(idPatient);
        return allTalonFromPatient.isEmpty() ?
                null : allTalonFromPatient.stream().map(TimeSlotMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<TimeSlotInfo> findAllFreeTimeSlotFromDoctor(Long idDoctor, LocalDate date) {
        final List<TimeSlot> allFreeTimeSlot = timeSlotRepository.findAllByDoctorIdAndDateAndPatientIdIsNull(idDoctor, date);
        return allFreeTimeSlot.isEmpty() ?
                null : allFreeTimeSlot.stream().map(TimeSlotMapper::toDtoFreeTimeSlot).collect(Collectors.toList());
    }

    @Override
    public void createSchedule(Long idDoctor, LocalDate date, LocalTime startTime, LocalTime shiftEndTime, Integer timeReceipt) {
        //первоначальные проверки
        if (date.isBefore(LocalDate.now()) || startTime.isAfter(shiftEndTime))
            throw new ValidatorException("Invalid date '" + date + "'  or start time receipt '" + startTime + "'");
        if (timeSlotRepository.existsByDateAndDoctorId(date,idDoctor)) throw new ValidatorException("Schedule is exist!");
        Doctor doctor = doctorRepository.findById(idDoctor).orElseThrow(
                () -> new NotFoundException("Doctor not found!"));
        //инициализируем первый талон
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setDoctor(doctor);
        timeSlot.setDate(date);
        timeSlot.setStartTime(startTime);
        timeSlot.setEndTime(startTime.plusMinutes(timeReceipt));

        if (timeSlot.getEndTime().isAfter(shiftEndTime))
            throw new ValidatorException("Time receipt after shift end time");
        timeSlotRepository.save(timeSlot);

        LocalTime previousEndTime = timeSlot.getEndTime().plusMinutes(timeReceipt);
        //Проверка что вмещаемся в смену врача
        while (previousEndTime.isBefore(shiftEndTime)) {
            LocalTime nexEndTime = previousEndTime.plusMinutes(timeReceipt).plusMinutes(5);
            timeSlot = new TimeSlot();
            timeSlot.setDoctor(doctor);
            timeSlot.setDate(date);
            timeSlot.setStartTime(previousEndTime.plusMinutes(5)); // Добавляем для завершения текущего приёма
            timeSlot.setEndTime(nexEndTime);
            previousEndTime = nexEndTime;
            timeSlotRepository.save(timeSlot);
        }
    }
}
