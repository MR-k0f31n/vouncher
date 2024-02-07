package ru.registration.controller.users;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.registration.dto.TimeSlotInfo;
import ru.registration.service.TimeSlotService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author MR.k0F31n
 */
@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class PatientRegistrationController {
    private final TimeSlotService timeSlotService;

    @GetMapping("/free/{doctorId}")
    public List<TimeSlotInfo> getAllFreeTimeSlotByDoctor(@PathVariable(value = "doctorId") Long doctorId,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return timeSlotService.findAllFreeTimeSlotFromDoctor(doctorId, date);
    }

    @GetMapping("/patient/{patientId}")
    public List<TimeSlotInfo> getAllTimeSlotByPatient(@PathVariable(value = "patientId") Long patientId) {
        return timeSlotService.findAllTimeSlotFromPatient(patientId);
    }

    @PostMapping("/register/")
    public TimeSlotInfo registrationTimeSlot(@RequestParam(value = "patientId") Long patientId,
                                             @RequestParam(value = "timeSlotId") Long timeSlotId) {
        return timeSlotService.registerPatient(timeSlotId, patientId);
    }
}
