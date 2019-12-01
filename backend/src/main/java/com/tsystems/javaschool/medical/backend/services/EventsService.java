package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.component.EventStatusChangerImpl;
import com.tsystems.javaschool.medical.backend.dao.EventRepository;
import com.tsystems.javaschool.medical.backend.dto.*;
import com.tsystems.javaschool.medical.backend.dto.enums.MessageStatus;
import com.tsystems.javaschool.medical.backend.entities.EventsEntity;
import com.tsystems.javaschool.medical.backend.exception.EventStatusChangerExeption;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventsService {

    private static final Logger Logger = LogManager.getLogger(EventsService.class);
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final EventStatusChangerImpl eventStatusChanger;

    @Autowired
    public EventsService(ModelMapper modelMapper, EventRepository eventRepository, EventStatusChangerImpl eventStatusChanger) {
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.eventStatusChanger = eventStatusChanger;
    }

    @Transactional(readOnly = true)
    public EventListResponse getEventsList(int start, int length, String orderBy, String orderDir) {

        EventListResponse eventListResponse = new EventListResponse();
        List<EventsDto> eventsList = new ArrayList<>();
        List<EventsEntity> eventsEntityList = eventRepository.getAll(start, length, orderBy, orderDir);
        long count = eventRepository.getCount();

        for (Object a : eventsEntityList) {
            EventsDto eventsDto = modelMapper.map(a, EventsDto.class);
            eventsList.add(eventsDto);
        }

        eventListResponse.setList(eventsList);
        eventListResponse.setRecords(count);
        return eventListResponse;
    }

    public void addEvent(EventRequestDto params) {
        eventRepository.create(params);
    }

    public void deleteEvent(int id) {
        eventRepository.delete(id);
    }

    public EventUpdateDto updateEvent(EventRequestDto params) {
        EventUpdateDto eventUpdateDto = new EventUpdateDto();
        List<MessageDto> messageDtoList = new ArrayList<>();
        MessageDto messageDto = new MessageDto();
        try {
            eventStatusChanger.changeStatus(params.getId(), params.getStatus());
            eventRepository.update(params);
        } catch (EventStatusChangerExeption e) {
            messageDto.setMessage(e.getMessage());
            messageDto.setStatus(MessageStatus.ERROR);
            messageDtoList.add(messageDto);
            eventUpdateDto.setMsg(messageDtoList);
            Logger.info(e.getMessage());
        }
        return eventUpdateDto;
    }

    @Transactional(readOnly = true)
    public List<EventsDto> getEventsListByPatient(int id) {
        List<EventsDto> eventsList = new ArrayList<>();
        List<EventsEntity> eventsEntityList = eventRepository.getEventByPatientId(id);

        for (Object a : eventsEntityList) {
            EventsDto eventsDto = modelMapper.map(a, EventsDto.class);
            eventsList.add(eventsDto);
        }
        return eventsList;
    }
}
