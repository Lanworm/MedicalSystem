package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.dto.EventsDto;
import com.tsystems.javaschool.medical.backend.entities.*;
import com.tsystems.javaschool.medical.backend.util.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

@Service
public class EventsService {

    private ModelMapper modelMapper = new ModelMapper();

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

    public void addEvent(EventRequestDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

        EventsEntity eventsEntity = new EventsEntity();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, params.getPatientId());
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, params.getProcedureId());
        RoomsEntity roomsEntity = session.load(RoomsEntity.class, params.getRoomId());
        StaffEntity staffEntity = session.load(StaffEntity.class, params.getStaffId());

        eventsEntity.setPatientByPatientId(patientsEntity);
        eventsEntity.setProcedureByProcedureId(proceduresEntity);
        eventsEntity.setRoomByRoomId(roomsEntity);
        eventsEntity.setStaffByStaffId(staffEntity);
        eventsEntity.setStatus(params.getStatus());
        eventsEntity.setStartDate(params.getStartDate());
        eventsEntity.setEndDate(params.getEndDate());
        eventsEntity.setCreatedAt(getCurrentTimestamp());
        eventsEntity.setUpdatedAt(getCurrentTimestamp());
        eventsEntity.setDeleted("N");

        session.persist(eventsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteEvent(int id) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        EventsEntity eventsEntity = session.load(EventsEntity.class, id);
        eventsEntity.setDeleted("Y");
        eventsEntity.setUpdatedAt(getCurrentTimestamp());
        eventsEntity.setId(id);
        session.update(eventsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void updateEvent(EventRequestDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

        EventsEntity eventsEntity = session.load(EventsEntity.class, params.getId());
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, params.getPatientId());
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, params.getProcedureId());
        RoomsEntity roomsEntity = session.load(RoomsEntity.class, params.getRoomId());
        StaffEntity staffEntity = session.load(StaffEntity.class, params.getStaffId());

        eventsEntity.setPatientByPatientId(patientsEntity);
        eventsEntity.setProcedureByProcedureId(proceduresEntity);
        eventsEntity.setRoomByRoomId(roomsEntity);
        eventsEntity.setStaffByStaffId(staffEntity);
        eventsEntity.setStatus(params.getStatus());
        eventsEntity.setStartDate(params.getStartDate());
        eventsEntity.setEndDate(params.getEndDate());
        eventsEntity.setUpdatedAt(getCurrentTimestamp());

        session.update(eventsEntity);
        session.getTransaction().commit();
        session.close();
    }
}
