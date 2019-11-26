package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.EventListResponse;
import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.EventUpdateDto;
import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.services.EventsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventsController {


    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping(value = "/events")
    public EventListResponse getEventsList(
            @RequestParam(value = "start") int page,
            @RequestParam(value = "length") int size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderDir") String orderDir
    ) {
        return eventsService.getEventsList(page, size, orderBy, orderDir);
    }

    @PutMapping(value = "/events")
    public void addEvent(@RequestBody EventRequestDto params) {
        eventsService.addEvent(params);
    }

    @DeleteMapping(value = "/events/{id}")
    public void deleteEvent(@PathVariable("id") int id) {
        eventsService.deleteEvent(id);
    }

    @PostMapping(value = "/events")
    public EventUpdateDto editEvent(@RequestBody EventRequestDto params) {
        return eventsService.updateEvent(params);
    }

    @GetMapping(value = "/eventsByPatient")
    public List<EventsDto> getEventsListByPatient(@RequestParam(value = "id") int id) {
        return eventsService.getEventsListByPatient(id);
    }
}
