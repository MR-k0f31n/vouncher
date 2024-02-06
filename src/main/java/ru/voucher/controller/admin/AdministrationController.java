package ru.voucher.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.voucher.service.TimeSlotService;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author MR.k0F31n
 */
@RestController
@RequestMapping("ad/schedule")
@RequiredArgsConstructor
public class AdministrationController {
    private final TimeSlotService timeSlotService;
    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createdSchedule(@RequestParam Long idDoctor,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                                @RequestParam @DateTimeFormat(pattern = "HH:mm")LocalTime startTime,
                                @RequestParam @DateTimeFormat(pattern = "HH:mm")LocalTime shiftEndTime,
                                @RequestParam(defaultValue = "15") Integer timeReceipt) {
        timeSlotService.createSchedule(idDoctor, date, startTime, shiftEndTime, timeReceipt);
    }
}
