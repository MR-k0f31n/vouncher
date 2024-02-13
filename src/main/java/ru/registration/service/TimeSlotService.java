package ru.registration.service;

import ru.registration.dto.TimeSlotInfo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author MR.k0F31n
 */
public interface TimeSlotService {
    /**
     * Register a patient for a free time slot.
     * Transmitted Long id time slot and Long id patient.
     * Return registration information from time slot.
     */
    TimeSlotInfo registerPatient(Long idTimeSlot, Long idPatient);

    /**
     * Get all registered patient time slots.
     * Transmitted Long id patient.
     * Return list of all registered time slots.
     */
    List<TimeSlotInfo> findAllTimeSlotFromPatient(Long idPatient);

    /**
     * Get all free time slots by doctor ID on a date.
     * Transmitted Long id doctor and LocalDate date.
     * Return list of all free time slots by doctor id on a date.
     */
    List<TimeSlotInfo> findAllFreeTimeSlotFromDoctor(Long idDoctor, LocalDate date);

    /**
     * Create a doctor's schedule according to the transmitted schedule for a specific date.
     * Transmitted Long id doctor, LocalDate Reception day, LocalTime Doctor's start time, LocalTime Doctor's end time, Integer time receipt in minutes
     */
    Integer createSchedule(Long idDoctor, LocalDate date, LocalTime startTime, LocalTime ShiftEndTime, Integer timeReceipt);
}
