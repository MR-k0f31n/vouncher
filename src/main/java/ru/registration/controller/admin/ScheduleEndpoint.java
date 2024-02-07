package ru.registration.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.registration.service.TimeSlotService;

/**
 * @author MR.k0F31n
 */
@Endpoint
@RequiredArgsConstructor
public class ScheduleEndpoint {
    private static final String NAMESPACE_URI = "http://localhost:8080/ru.vouncher/ad/shedule/create";

    private final TimeSlotService timeSlotService;
}
