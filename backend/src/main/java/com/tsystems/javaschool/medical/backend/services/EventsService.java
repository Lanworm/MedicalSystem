package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.entities.EventsEntity;
import com.tsystems.javaschool.medical.backend.util.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventsService {

    private ModelMapper modelMapper = new ModelMapper();
    private Date date = new Date();

    public List<EventsDto> getEventsList() {

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(EventsEntity.class);
        criteria.add(Restrictions.eq("deleted", "N"));

        List list = criteria.list();
        List<EventsDto> result = new ArrayList<>();

        for (Object a : list) {
            EventsDto eventsDto = modelMapper.map(a, EventsDto.class);
            result.add(eventsDto);
        }

        session.getTransaction().commit();
        session.close();
        return result;
    }
}
