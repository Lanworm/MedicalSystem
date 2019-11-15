package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.EventUpdateDto;
import com.tsystems.javaschool.medical.backend.services.EventsService;
import com.tsystems.javaschool.medical.backend.util.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventsController {


    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public BaseResponse getEventsList(
            @RequestParam(value = "start") int page,
            @RequestParam(value = "length") int size,
            @RequestParam(value = "orderBy") String orderBy,
            @RequestParam(value = "orderDir") String orderDir

    ) {
        return eventsService.getEventsList(page, size, orderBy, orderDir);
    }

    @RequestMapping(value = "/events", method = RequestMethod.PUT)
    public void addEvent(@RequestBody EventRequestDto params) {
        eventsService.addEvent(params);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("id") int id) {
        eventsService.deleteEvent(id);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public EventUpdateDto editEvent(@RequestBody EventRequestDto params) {
        return eventsService.updateEvent(params);
    }
}
