package com.tsystems.javaschool.medical.backend.component;

import com.tsystems.javaschool.medical.backend.CustomExeption;
import com.tsystems.javaschool.medical.backend.component.enums.EventStatus;
import com.tsystems.javaschool.medical.backend.dao.EventRepository;
import com.tsystems.javaschool.medical.backend.entities.EventsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventStatusChangerImpl implements EventStatusChanger {

    private final EventRepository eventRepository;

    @Autowired
    public EventStatusChangerImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void changeStatus(int eventId, String newStatus) throws CustomExeption {
        EventsEntity eventsEntity = eventRepository.getEventById(eventId);
        String eventStatus = eventsEntity.getStatus();
        if (eventStatus.equals(EventStatus.IN_WORK.getValue())  && newStatus.equals(EventStatus.ASSIGNED.getValue())) {
            throw new CustomExeption("Event status can not be changet to " + newStatus + " because he is already in status " + eventStatus);
        }
    }
}
