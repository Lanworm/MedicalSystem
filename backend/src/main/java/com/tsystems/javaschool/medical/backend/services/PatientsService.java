package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.PatientsDto;
import com.tsystems.javaschool.medical.backend.entities.PatientsEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

@Service
public class PatientsService {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SessionFactory sessionFactory;

    public List<PatientsDto> getPatientsList() {

        Session session = sessionFactory.openSession();
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
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        PatientsEntity patientsEntity = new PatientsEntity();
        patientsEntity.setFirstName(firstName);
        patientsEntity.setSecondName(secondName);
        patientsEntity.setLastName(lastName);
        patientsEntity.setInsuranceNumber(insuranceNumber);
        patientsEntity.setCreatedAt(getCurrentTimestamp());
        patientsEntity.setUpdatedAt(getCurrentTimestamp());
        patientsEntity.setDeleted("N");
        session.persist(patientsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deletePatient(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, id);
        patientsEntity.setDeleted("Y");
        patientsEntity.setUpdatedAt(getCurrentTimestamp());
        patientsEntity.setId(id);
        session.update(patientsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void updatePatient(PatientsDto params) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        PatientsEntity patientsEntity = session.load(PatientsEntity.class, params.getId());
        patientsEntity.setUpdatedAt(getCurrentTimestamp());
        patientsEntity.setFirstName(params.getFirstName());
        patientsEntity.setSecondName(params.getSecondName());
        patientsEntity.setLastName(params.getLastName());
        patientsEntity.setInsuranceNumber(params.getInsuranceNumber());
        session.update(patientsEntity);
        session.getTransaction().commit();
        session.close();
    }
}
