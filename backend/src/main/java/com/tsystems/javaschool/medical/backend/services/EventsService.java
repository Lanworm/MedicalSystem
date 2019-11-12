package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dao.EventRepository;
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

    private final ModelMapper modelMapper;

    private final EventRepository eventRepository;

    @Autowired
    public EventsService(ModelMapper modelMapper, EventRepository eventRepository) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
    }

    public BaseResponse getEventsList(int start, int length, String orderBy, String orderDir) {

        BaseResponse baseResponse = new BaseResponse();
        List<EventsDto> eventsList = new ArrayList<>();
        List<EventsEntity> eventsEntityList = eventRepository.getAll(start, length, orderBy, orderDir);

        for (Object a : eventsEntityList) {
            EventsDto eventsDto = modelMapper.map(a, EventsDto.class);
            eventsList.add(eventsDto);
        }

        baseResponse.getList().addAll(eventsList);
        baseResponse.setRecords(eventRepository.getCount());
        return baseResponse;
    }

    public void addEvent(EventRequestDto params) {
        eventRepository.create(params);
    }

    public void deleteEvent(int id) {
        eventRepository.delete(id);
    }

    public void updateEvent(EventRequestDto params) {
        eventRepository.update(params);
    }
}
