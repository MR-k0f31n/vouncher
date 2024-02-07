package ru.registration.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author MR.k0F31n
 */
@Builder
@Getter
@Setter
public class TimeSlotInfo {
    private Long id;
    private String fullNamePatient;
    private String fullNameDoctor;
    private String specialization;
    private LocalDate date;
    private LocalTime startTime;
}
