package com.tsystems.javaschool.medical.backend.component.impl;

import com.tsystems.javaschool.medical.backend.component.EventStatusChanger;
import com.tsystems.javaschool.medical.backend.component.enums.EventStatus;
import com.tsystems.javaschool.medical.backend.dao.EventRepository;
import com.tsystems.javaschool.medical.backend.entities.EventsEntity;
import com.tsystems.javaschool.medical.backend.exception.EventStatusChangerExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class EventStatusChangerImpl implements EventStatusChanger {

    private final EventRepository eventRepository;

    @Autowired
    public EventStatusChangerImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void changeStatus(BigInteger eventId, String newStatus) throws EventStatusChangerExeption {
        EventsEntity eventsEntity = eventRepository.getEventById(eventId);
        String eventStatus = eventsEntity.getStatus();
        if (eventStatus.equals(EventStatus.IN_WORK.getValue())  && newStatus.equals(EventStatus.ASSIGNED.getValue())) {
            throw new EventStatusChangerExeption("Event status can not be changet to " + newStatus + " because he is already in status " + eventStatus);
        }
    }
}
