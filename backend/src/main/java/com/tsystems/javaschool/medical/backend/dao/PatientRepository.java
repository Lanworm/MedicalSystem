package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
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
public class PatientRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<PatientsEntity> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(PatientsEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.add(Restrictions.eq("deleted", "N"));
        List<PatientsEntity> patientsEntities = criteria.list();

        return patientsEntities;
    }

    @Transactional
    public void create(String firstName, String secondName, String lastName, String insuranceNumber) {
        Session session = sessionFactory.getCurrentSession();
        PatientsEntity patientsEntity = new PatientsEntity();
        patientsEntity.setFirstName(firstName);
        patientsEntity.setSecondName(secondName);
        patientsEntity.setLastName(lastName);
        patientsEntity.setInsuranceNumber(insuranceNumber);
        patientsEntity.setCreatedAt(getCurrentTimestamp());
        patientsEntity.setUpdatedAt(getCurrentTimestamp());
        patientsEntity.setDeleted("N");
        session.persist(patientsEntity);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, id);
        patientsEntity.setDeleted("Y");
        patientsEntity.setUpdatedAt(getCurrentTimestamp());
        patientsEntity.setId(id);
        session.update(patientsEntity);
    }

    @Transactional
    public void update(PatientsDto params) {
        Session session = sessionFactory.getCurrentSession();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, params.getId());
        patientsEntity.setUpdatedAt(getCurrentTimestamp());
        patientsEntity.setFirstName(params.getFirstName());
        patientsEntity.setSecondName(params.getSecondName());
        patientsEntity.setLastName(params.getLastName());
        patientsEntity.setInsuranceNumber(params.getInsuranceNumber());
        session.update(patientsEntity);
    }

    @Transactional
    public PatientsEntity getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(PatientsEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (PatientsEntity) criteria.uniqueResult();
    }
}
