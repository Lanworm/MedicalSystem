package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<EventsDto> getEventsList() {
        return eventsService.getEventsList();
    }

    @RequestMapping(value = "/events", method = RequestMethod.PUT)
    public List<EventsDto> addEvent(@RequestBody EventRequestDto params) {
        eventsService.addEvent(params);
        return eventsService.getEventsList();
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public List<EventsDto> deleteEvent(@PathVariable("id") int id) {
        eventsService.deleteEvent(id);
        return eventsService.getEventsList();
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public List< EventsDto> editEvent(@RequestBody EventRequestDto params) {
        eventsService.updateEvent(params);
        return eventsService.getEventsList();
    }
}
