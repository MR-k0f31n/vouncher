package ru.registration.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.registration.service.TimeSlotService;

import registration.ru.GetCreateScheduleResponse;
import registration.ru.GetCreateScheduleRequest;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author MR.k0F31n
 */
@Endpoint
@RequiredArgsConstructor
public class ScheduleEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/ru.vouncher/ad/shedule/create";
    private final TimeSlotService timeSlotService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCreateScheduleRequest")
    @ResponsePayload
    public GetCreateScheduleResponse createScheduleResponse(@RequestPayload GetCreateScheduleRequest request) {
        GetCreateScheduleResponse response = new GetCreateScheduleResponse();
        XMLGregorianCalendar startTime = request.getStartTime();
        LocalTime startTimeFormat = LocalTime.of(startTime.getHour(), startTime.getMinute(), startTime.getSecond());

        XMLGregorianCalendar date = request.getDate();
        LocalDate dateFormat = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());

        XMLGregorianCalendar shiftEndTime = request.getShiftEndTime();
        LocalTime shiftEndTimeFormat = LocalTime.of(shiftEndTime.getHour(), shiftEndTime.getMinute(), shiftEndTime.getSecond());

        Integer countTimeSlot = timeSlotService.createSchedule(
                request.getIdDoctor(),
                dateFormat,
                startTimeFormat,
                shiftEndTimeFormat,
                request.getTimeReceipt()
        );

        response.setCountTimeSlot(countTimeSlot);
        return response;
    }

}
