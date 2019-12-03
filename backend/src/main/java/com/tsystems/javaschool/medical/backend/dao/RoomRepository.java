package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.dto.RoomsDto;
import com.tsystems.javaschool.medical.backend.entities.RoomsEntity;
import com.tsystems.javaschool.medical.backend.entities.enums.IsDeleted;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoomRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<RoomsEntity> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(RoomsEntity.class);
        criteria.addOrder(Order.asc("description"));
        criteria.add(Restrictions.eq("deleted", IsDeleted.N));
        List<RoomsEntity> roomsEntities = criteria.list();
        return roomsEntities;
    }

    @Transactional
    public void create(String description) {
        RoomsEntity roomsEntity = new RoomsEntity();
        roomsEntity.setDescription(description);
        Session session = sessionFactory.getCurrentSession();
        session.persist(roomsEntity);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        RoomsEntity roomsEntity = session.load(RoomsEntity.class, id);
        roomsEntity.setDeleted(IsDeleted.Y);
        session.update(roomsEntity);
    }

    @Transactional
    public void update(RoomsDto params) {
        Session session = sessionFactory.getCurrentSession();
        RoomsEntity roomsEntity = session.load(RoomsEntity.class, params.getId());
        roomsEntity.setDescription(params.getDescription());
        session.update(roomsEntity);
    }
}
