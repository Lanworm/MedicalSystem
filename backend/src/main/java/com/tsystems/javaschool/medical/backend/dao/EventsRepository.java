package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.dto.EventRequestDto;
import com.tsystems.javaschool.medical.backend.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

@Repository
public class EventsRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Transactional
    public List<EventsEntity> getAll(int start, int length, String orderBy, String orderDir) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(EventsEntity.class);
        criteria.add(Restrictions.eq("deleted", "N"));
        criteria.setMaxResults(length);
        criteria.setFirstResult(start);

        Criteria crit = criteria.createCriteria("patientByPatientId");
        crit.addOrder(Order.desc("firstName"));

//        if (orderBy != null && !orderBy.isEmpty() && orderDir != null && !orderDir.isEmpty()) {
//            if (orderDir.equals("desc")) {
//                criteria.addOrder(Order.desc(orderBy));
//            } else if (orderDir.equals("asc")) {
//                criteria.addOrder(Order.asc(orderBy));
//            }
//        }

        List<EventsEntity> eventsEntityList = criteria.list();

        return eventsEntityList;
    }

    @Transactional
    public long getCount() {
        Session session = sessionFactory.getCurrentSession();

        String hql = "select count(*) from  EventsEntity";
        Query query = session.createQuery(hql);
        Long count = (Long) query.uniqueResult();

        return count;
    }

    @Transactional
    public void create(EventRequestDto params) {
        Session session = sessionFactory.getCurrentSession();

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

    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        EventsEntity eventsEntity = session.load(EventsEntity.class, id);
        eventsEntity.setDeleted("Y");
        eventsEntity.setUpdatedAt(getCurrentTimestamp());
        eventsEntity.setId(id);
        session.update(eventsEntity);
    }

    @Transactional
    public void update(EventRequestDto params) {
        Session session = sessionFactory.getCurrentSession();
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
    }
}
