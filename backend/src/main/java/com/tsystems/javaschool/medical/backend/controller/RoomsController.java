package com.tsystems.javaschool.medical.backend.controller;

import com.tsystems.javaschool.medical.backend.dto.RoomsDto;
import com.tsystems.javaschool.medical.backend.services.RoomsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }


    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public List<RoomsDto> getRooms() {
        return roomsService.getRoomsList();
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.PUT)
    public List<RoomsDto> addRoom(
            @RequestParam(value = "description") String description
    ) {
        roomsService.addRoom(description);
        return roomsService.getRoomsList();
    }

    @RequestMapping(value = "/rooms/{id}", method = RequestMethod.DELETE)
    public List<RoomsDto> deleteRoom(@PathVariable("id") final int Id) {
        roomsService.deleteRoom(Id);
        return roomsService.getRoomsList();
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public List<RoomsDto> editRoom(@RequestBody final RoomsDto params) {
        roomsService.updateRoom(params);
        return roomsService.getRoomsList();
    }

}
