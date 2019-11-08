package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.StaffDto;
import com.tsystems.javaschool.medical.backend.entities.SpecializationsEntity;
import com.tsystems.javaschool.medical.backend.entities.StaffEntity;
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
public class StaffService {
    private ModelMapper modelMapper = new ModelMapper();

    public List<StaffDto> getStaffList() {

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(StaffEntity.class);
        criteria.addOrder(Order.asc("lastName"));
        criteria.add(Restrictions.eq("deleted", "N"));

        List list = criteria.list();
        List<StaffDto> result = new ArrayList<>();

        for (Object a : list) {
            StaffDto staffDto = modelMapper.map(a, StaffDto.class);
            result.add(staffDto);
        }

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void addStaff(String firstName, String secondName, String lastName, int specializationId) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

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
        session.getTransaction().commit();
        session.close();
    }

    public void deleteStaff(int id) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

        StaffEntity staffsEntity = session.load(StaffEntity.class, id);
        staffsEntity.setDeleted("Y");
        staffsEntity.setUpdatedAt(getCurrentTimestamp());
        staffsEntity.setId(id);

        session.update(staffsEntity);

        session.getTransaction().commit();
        session.close();
    }
//
    public void updateSpecialization(StaffDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

        StaffEntity staffsEntity = session.load(StaffEntity.class, params.getId());
        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, params.getSpecializationId());
        staffsEntity.setUpdatedAt(getCurrentTimestamp());

        staffsEntity.setFirstName(params.getFirstName());
        staffsEntity.setSecondName(params.getSecondName());
        staffsEntity.setLastName(params.getLastName());
        staffsEntity.setSpecializationsBySpecializationId(specializationsEntity);


        session.update(specializationsEntity);

        session.getTransaction().commit();
        session.close();
    }
}
