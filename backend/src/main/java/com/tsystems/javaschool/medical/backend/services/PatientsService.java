package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
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
public class PatientsService {
    private ModelMapper modelMapper = new ModelMapper();
    private Date date = new Date();
    private Timestamp currentDate = new Timestamp(date.getTime());


    public List<PatientsDto> getPatientsList() {

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(PatientsEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.add(Restrictions.eq("deleted", "N"));

        List list = criteria.list();
        List<PatientsDto> result = new ArrayList<>();

        for (Object a : list) {
            PatientsDto patientsDto = modelMapper.map(a, PatientsDto.class);
            result.add(patientsDto);
        }

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void addPatient(String firstName, String secondName, String lastName, String insuranceNumber) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        PatientsEntity patientsEntity = new PatientsEntity();
        patientsEntity.setFirstName(firstName);
        patientsEntity.setSecondName(secondName);
        patientsEntity.setLastName(lastName);
        patientsEntity.setInsuranceNumber(insuranceNumber);
        patientsEntity.setCreatedAt(currentDate);
        patientsEntity.setUpdatedAt(currentDate);
        patientsEntity.setDeleted("N");
        session.persist(patientsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePatient(int id) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, id);
        patientsEntity.setDeleted("Y");
        patientsEntity.setUpdatedAt(currentDate);
        patientsEntity.setId(id);
        session.update(patientsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void updatePatient(PatientsDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, params.getId());
        patientsEntity.setUpdatedAt(currentDate);
        patientsEntity.setFirstName(params.getFirstName());
        patientsEntity.setSecondName(params.getSecondName());
        patientsEntity.setLastName(params.getLastName());
        patientsEntity.setInsuranceNumber(params.getInsuranceNumber());
        session.update(patientsEntity);
        session.getTransaction().commit();
        session.close();
    }
}
