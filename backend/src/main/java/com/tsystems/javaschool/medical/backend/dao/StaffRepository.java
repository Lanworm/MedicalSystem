package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.dto.StaffDto;
import com.tsystems.javaschool.medical.backend.entities.SpecializationsEntity;
import com.tsystems.javaschool.medical.backend.entities.StaffEntity;
import org.hibernate.Criteria;
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
public class StaffRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    @Transactional
    public List<StaffEntity> getAll() {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(StaffEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.add(Restrictions.eq("deleted", "N"));

        List<StaffEntity> staffEntityList = criteria.list();
        return staffEntityList;
    }

    @Transactional
    public void create(String firstName, String secondName, String lastName, int specializationId) {
        Session session = sessionFactory.getCurrentSession();

        StaffEntity staffEntity = new StaffEntity();
        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, specializationId);

        staffEntity.setFirstName(firstName);
        staffEntity.setSecondName(secondName);
        staffEntity.setLastName(lastName);
        staffEntity.setSpecializationsBySpecializationId(specializationsEntity);
        staffEntity.setCreatedAt(getCurrentTimestamp());
        staffEntity.setUpdatedAt(getCurrentTimestamp());
        staffEntity.setDeleted("N");

        session.persist(staffEntity);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();

        StaffEntity staffsEntity = session.load(StaffEntity.class, id);
        staffsEntity.setDeleted("Y");
        staffsEntity.setUpdatedAt(getCurrentTimestamp());
        staffsEntity.setId(id);

        session.update(staffsEntity);
    }

    @Transactional
    public void update(StaffDto params) {
        Session session = sessionFactory.getCurrentSession();

        StaffEntity staffsEntity = session.load(StaffEntity.class, params.getId());
        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, params.getSpecializationId());

        staffsEntity.setUpdatedAt(getCurrentTimestamp());
        staffsEntity.setFirstName(params.getFirstName());
        staffsEntity.setSecondName(params.getSecondName());
        staffsEntity.setLastName(params.getLastName());
        staffsEntity.setSpecializationsBySpecializationId(specializationsEntity);

        session.update(specializationsEntity);
    }
}
