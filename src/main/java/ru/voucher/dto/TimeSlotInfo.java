package ru.voucher.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author MR.k0F31n
 */
@Builder
public class TimeSlotInfo {
    private String fullNamePatient;
    private String fullNameDoctor;
    private String Specialization;
    private LocalDate date;
    private LocalTime startTime;
}
