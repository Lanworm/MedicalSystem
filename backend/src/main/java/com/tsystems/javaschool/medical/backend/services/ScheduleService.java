package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.SpecializationsDto;
import com.tsystems.javaschool.medical.backend.entities.SpecializationsEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static com.tsystems.javaschool.medical.backend.util.DateUtils.getCurrentTimestamp;

public class ScheduleService {

    @Autowired
    private SessionFactory sessionFactory;

    private ModelMapper modelMapper = new ModelMapper();

    public List<SpecializationsDto> getSchedule() {

        Session session = sessionFactory.openSession();
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
        specializationsEntity.setCreatedAt(getCurrentTimestamp());
        specializationsEntity.setUpdatedAt(getCurrentTimestamp());
        specializationsEntity.setDeleted("N");

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(specializationsEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteSpecialization(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, id);
        specializationsEntity.setDeleted("Y");
        specializationsEntity.setUpdatedAt(getCurrentTimestamp());
        specializationsEntity.setId(id);

        session.update(specializationsEntity);

        session.getTransaction().commit();
        session.close();
    }

    public void updateSpecialization(SpecializationsDto params) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        SpecializationsEntity specializationsEntity = session.load(SpecializationsEntity.class, params.getId());
        specializationsEntity.setUpdatedAt(getCurrentTimestamp());
        specializationsEntity.setId(params.getId());
        specializationsEntity.setDescription(params.getDescription());

        session.update(specializationsEntity);

        session.getTransaction().commit();
        session.close();
    }
}
