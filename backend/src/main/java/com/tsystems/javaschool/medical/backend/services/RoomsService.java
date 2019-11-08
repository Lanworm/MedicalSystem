package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.RoomsDto;
import com.tsystems.javaschool.medical.backend.entities.RoomsEntity;
import com.tsystems.javaschool.medical.backend.util.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

@Service
public class RoomsService {
    private ModelMapper modelMapper = new ModelMapper();

    public List<RoomsDto> getRoomsList() {

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(RoomsEntity.class);
        criteria.addOrder(Order.asc("description"));
        criteria.add(Restrictions.eq("deleted", "N"));
        List list = criteria.list();
        List<RoomsDto> result = new ArrayList<>();
        for (Object a : list) {
            RoomsDto roomsDto = modelMapper.map(a, RoomsDto.class);
            result.add(roomsDto);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void addRoom(String description) {
        RoomsEntity roomsEntity = new RoomsEntity();
        roomsEntity.setDescription(description);
        roomsEntity.setCreatedAt(getCurrentTimestamp());
        roomsEntity.setUpdatedAt(getCurrentTimestamp());
        roomsEntity.setDeleted("N");
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(roomsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteRoom(int id) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        RoomsEntity roomsEntity = session.load(RoomsEntity.class, id);
        roomsEntity.setDeleted("Y");
        roomsEntity.setUpdatedAt(getCurrentTimestamp());
        roomsEntity.setId(id);
        session.update(roomsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void updateRoom(RoomsDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        RoomsEntity roomsEntity = session.load(RoomsEntity.class, params.getId());
        roomsEntity.setUpdatedAt(getCurrentTimestamp());
        roomsEntity.setId(params.getId());
        roomsEntity.setDescription(params.getDescription());
        session.update(roomsEntity);
        session.getTransaction().commit();
        session.close();
    }
}
