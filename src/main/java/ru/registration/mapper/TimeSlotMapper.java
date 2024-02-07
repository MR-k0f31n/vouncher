package ru.registration.mapper;

import ru.registration.dto.TimeSlotInfo;
import ru.registration.model.TimeSlot;

/**
 * @author MR.k0F31n
 */
public class TimeSlotMapper {
    public static TimeSlotInfo toDto(TimeSlot timeSlot) {
        return TimeSlotInfo.builder()
                .fullNamePatient(timeSlot.getPatient().getFirstname() + " " + timeSlot.getPatient().getLastname())
                .fullNameDoctor(timeSlot.getDoctor().getFirstname() + " " + timeSlot.getDoctor().getLastname())
                .specialization(timeSlot.getDoctor().getSpecialization().toString())
                .startTime(timeSlot.getStartTime())
                .date(timeSlot.getDate())
                .build();
    }

    public static TimeSlotInfo toDtoFreeTimeSlot(TimeSlot timeSlot) {
        return TimeSlotInfo.builder()
                .id(timeSlot.getId())
                .fullNameDoctor(timeSlot.getDoctor().getFirstname() + " " + timeSlot.getDoctor().getLastname())
                .specialization(timeSlot.getDoctor().getSpecialization().toString())
                .startTime(timeSlot.getStartTime())
                .date(timeSlot.getDate())
                .build();
    }
}
