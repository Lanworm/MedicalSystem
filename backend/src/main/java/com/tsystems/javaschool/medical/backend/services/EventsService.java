package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.EventsRepository;
import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.entities.EventsEntity;
import com.tsystems.javaschool.medical.backend.util.BaseResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EventsRepository eventsRepository;

    public BaseResponse getEventsList(int start, int length, String orderBy, String orderDir) {

        BaseResponse baseResponse = new BaseResponse();
        List<EventsDto> eventsList = new ArrayList<>();
        List<EventsEntity> eventsEntityList = eventsRepository.getAll(start, length, orderBy, orderDir);
        for (Object a : eventsEntityList) {
            EventsDto eventsDto = modelMapper.map(a, EventsDto.class);
            eventsList.add(eventsDto);
        }
        baseResponse.getList().addAll(eventsList);
        baseResponse.setRecords(eventsRepository.getCount());
        return baseResponse;
    }

    public void addEvent(EventRequestDto params) {
        eventsRepository.create(params);
    }

    public void deleteEvent(int id) {
        eventsRepository.delete(id);
    }

    public void updateEvent(EventRequestDto params) {
        eventsRepository.update(params);
    }
}
