package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.component.EventStatusChangerImpl;
import com.tsystems.javaschool.medical.backend.dao.EventRepository;
import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.EventUpdateDto;
import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.dto.MsgDto;
import com.tsystems.javaschool.medical.backend.dto.enums.MsgStatus;
import com.tsystems.javaschool.medical.backend.entities.EventsEntity;
import com.tsystems.javaschool.medical.backend.exception.EventStatusChangerExeption;
import com.tsystems.javaschool.medical.backend.util.BaseResponse;
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

    static final Logger rootLogger = LogManager.getLogger(EventsService.class);
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
    public BaseResponse getEventsList(int start, int length, String orderBy, String orderDir) {

        BaseResponse baseResponse = new BaseResponse();
        List<EventsDto> eventsList = new ArrayList<>();
        List<EventsEntity> eventsEntityList = eventRepository.getAll(start, length, orderBy, orderDir);
        long count = eventRepository.getCount();

        for (Object a : eventsEntityList) {
            EventsDto eventsDto = modelMapper.map(a, EventsDto.class);
            eventsList.add(eventsDto);
        }

        baseResponse.getList().addAll(eventsList);
        baseResponse.setRecords(count);
        return baseResponse;
    }

    public void addEvent(EventRequestDto params) {
        eventRepository.create(params);
    }

    public void deleteEvent(int id) {
        eventRepository.delete(id);
    }

    public EventUpdateDto updateEvent(EventRequestDto params) {
        EventUpdateDto eventUpdateDto = new EventUpdateDto();
        List<MsgDto> msgDtoList = new ArrayList<>();
        MsgDto msgDto = new MsgDto();
        try {
            eventStatusChanger.changeStatus(params.getId(), params.getStatus());
            eventRepository.update(params);
        }
        catch (EventStatusChangerExeption e){
            msgDto.setMessage(e.getMessage());
            msgDto.setStatus(MsgStatus.ERROR);
            msgDtoList.add(msgDto);
            eventUpdateDto.setMsg(msgDtoList);
            rootLogger.error(e.getMessage());
        }
        return eventUpdateDto;
    }
}
