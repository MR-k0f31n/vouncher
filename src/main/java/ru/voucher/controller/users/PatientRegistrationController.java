package ru.voucher.controller.users;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.voucher.dto.TimeSlotInfo;
import ru.voucher.service.TimeSlotService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MR.k0F31n
 */
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class PatientRegistrationController {
    private final TimeSlotService timeSlotService;

    @GetMapping("/{doctorId}")
    public List<TimeSlotInfo> getAllFreeTimeSlotByDoctor(@PathVariable(value = "doctorId") Long doctorId,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return timeSlotService.findAllFreeTimeSlotFromDoctor(doctorId, date);
    }

    @GetMapping("/{patientId}")
    public List<TimeSlotInfo> getAllTimeSlotByPatient(@PathVariable(value = "patientId") Long patientId) {
        return timeSlotService.findAllTimeSlotFromPatient(patientId);
    }

    @PostMapping("/{patientId}&{timeSlotId}")
    public TimeSlotInfo registrationTimeSlot(@PathVariable(value = "patientId") Long patientId,
                                             @PathVariable(value = "timeSlotId") Long timeSlotId) {
        return timeSlotService.registerPatient(timeSlotId, patientId);
    }
}
