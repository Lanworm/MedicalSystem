package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.services.EventsService;
import com.tsystems.javaschool.medical.backend.util.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

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
    public BaseResponse addEvent(@RequestBody EventRequestDto params) {
        eventsService.addEvent(params);
        return eventsService.getEventsList(1, 10, null, null);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public BaseResponse deleteEvent(@PathVariable("id") int id) {
        eventsService.deleteEvent(id);
        return eventsService.getEventsList(1, 10, null, null);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public BaseResponse editEvent(@RequestBody EventRequestDto params) {
        eventsService.updateEvent(params);
        return eventsService.getEventsList(1, 10, null, null);
    }
}
