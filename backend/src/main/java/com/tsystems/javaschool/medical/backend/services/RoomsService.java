package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.RoomRepository;
import com.tsystems.javaschool.medical.backend.dto.RoomsDto;
import com.tsystems.javaschool.medical.backend.entities.RoomsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoomRepository roomRepository;

    public List<RoomsDto> getRoomsList() {
        List<RoomsEntity> roomsEntities = roomRepository.getAll();
        List<RoomsDto> result = new ArrayList<>();
        for (Object entity : roomsEntities) {
            RoomsDto roomsDto = modelMapper.map(entity, RoomsDto.class);
            result.add(roomsDto);
        }

        return result;
    }

    public void addRoom(String description) {
        roomRepository.create(description);
    }

    public void deleteRoom(int id) {
        roomRepository.delete(id);
    }

    public void updateRoom(RoomsDto params) {
        roomRepository.update(params);
    }
}
