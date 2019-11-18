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
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

@Repository
public class EventRepository {

    private static final HashMap<String, HashMap<String, String>> FILTER_MAP;
    private static final String TABLE_KEY = "table";
    private static final String FIELD_KEY = "field";

    static {
        FILTER_MAP = new HashMap<>();
        FILTER_MAP.put("patient", prepareFilterMapValue("patientByPatientId", "firstName"));
        FILTER_MAP.put("room", prepareFilterMapValue("roomByRoomId", "description"));
        FILTER_MAP.put("procedure", prepareFilterMapValue("procedureByProcedureId", "description"));
        FILTER_MAP.put("doctor", prepareFilterMapValue("staffByStaffId", "firstName"));
        FILTER_MAP.put("from", prepareFilterMapValue(null, "startDate"));
        FILTER_MAP.put("to", prepareFilterMapValue(null, "endDate"));
        FILTER_MAP.put("status", prepareFilterMapValue(null, "status"));
    }

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @NonNull
    private static HashMap<String, String> prepareFilterMapValue(@NonNull String key, @NonNull String value) {
        final HashMap<String, String> result = new HashMap<>();
        result.put(TABLE_KEY, key);
        result.put(FIELD_KEY, value);
        return result;
    }


    public List<EventsEntity> getAll(int start, int length, String orderBy, String orderDir) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(EventsEntity.class);
        criteria.add(Restrictions.eq("deleted", "N"));
        criteria.setMaxResults(length);
        criteria.setFirstResult(start);

        if (FILTER_MAP.containsKey(orderBy)) {
            HashMap params = FILTER_MAP.get(orderBy);
            if (params.get(TABLE_KEY) == null) {
                if (orderDir.equals("desc")) {
                    criteria.addOrder(Order.desc(params.get(FIELD_KEY).toString()));
                } else if (orderDir.equals("asc")) {
                    criteria.addOrder(Order.asc(params.get(FIELD_KEY).toString()));
                }
            } else {
                Criteria secondCriteria = criteria.createCriteria(params.get(TABLE_KEY).toString());
                if (orderDir.equals("desc")) {
                    secondCriteria.addOrder(Order.desc(params.get(FIELD_KEY).toString()));
                } else if (orderDir.equals("asc")) {
                    secondCriteria.addOrder(Order.asc(params.get(FIELD_KEY).toString()));
                }
            }
        }

        List<EventsEntity> eventsEntityList = criteria.list();

        return eventsEntityList;
    }


    public long getCount() {
        Session session = sessionFactory.getCurrentSession();

        String hql = "select count(*) from  EventsEntity where deleted='N'";
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

    @Transactional
    public EventsEntity getEventById(int eventId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(EventsEntity.class);
        criteria.add(Restrictions.eq("id", eventId));
        EventsEntity result = (EventsEntity) criteria.uniqueResult();
        return result;
    }
}
