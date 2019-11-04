package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.SpecializationsDto;
import com.tsystems.javaschool.medical.backend.entities.SpecializationsEntity;
import com.tsystems.javaschool.medical.backend.util.HibernateSessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScheduleService {

    private ModelMapper modelMapper = new ModelMapper();
    private Date date = new Date();
    private Timestamp currentDate = new Timestamp(date.getTime());

    public List<SpecializationsDto> getSchedule() {

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(SpecializationsEntity.class);
        criteria.addOrder(Order.asc("description"));
        criteria.add(Restrictions.eq("deleted", "N"));

        List list = criteria.list();
        List<SpecializationsDto> result = new ArrayList<>();

        for (Object a : list) {
            SpecializationsDto specializationsDto = modelMapper.map(a, SpecializationsDto.class);
            result.add(specializationsDto);
        }

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void addSchedule(String description) {
        SpecializationsEntity specializationsEntity = new SpecializationsEntity();

        specializationsEntity.setDescription(description);
        specializationsEntity.setCreatedAt(currentDate);
        specializationsEntity.setUpdatedAt(currentDate);
        specializationsEntity.setDeleted("N");

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(specializationsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSpecialization(int id) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, id);
        specializationsEntity.setDeleted("Y");
        specializationsEntity.setUpdatedAt(currentDate);
        specializationsEntity.setId(id);

        session.update(specializationsEntity);

        session.getTransaction().commit();
        session.close();
    }

    public void updateSpecialization(SpecializationsDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();

        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, params.getId());
        specializationsEntity.setUpdatedAt(currentDate);
        specializationsEntity.setId(params.getId());
        specializationsEntity.setDescription(params.getDescription());

        session.update(specializationsEntity);

        session.getTransaction().commit();
        session.close();
    }
}
