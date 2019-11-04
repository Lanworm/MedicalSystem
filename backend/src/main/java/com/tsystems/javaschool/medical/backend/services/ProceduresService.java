package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.ProceduresDto;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
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
public class ProceduresService {
    private ModelMapper modelMapper = new ModelMapper();
    private Date date = new Date();
    private Timestamp currentDate = new Timestamp(date.getTime());

    public List<ProceduresDto> getProceduresList() {

        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(ProceduresEntity.class);
        criteria.addOrder(Order.asc("description"));
        criteria.add(Restrictions.eq("deleted", "N"));
        List list = criteria.list();
        List<ProceduresDto> result = new ArrayList<>();
        for (Object a : list) {
            ProceduresDto proceduresDto = modelMapper.map(a, ProceduresDto.class);
            result.add(proceduresDto);
        }
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void addProcedure(String description) {
        ProceduresEntity proceduresEntity = new ProceduresEntity();
        proceduresEntity.setDescription(description);
        proceduresEntity.setCreatedAt(currentDate);
        proceduresEntity.setUpdatedAt(currentDate);
        proceduresEntity.setDeleted("N");
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(proceduresEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProcedure(int id) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, id);
        proceduresEntity.setDeleted("Y");
        proceduresEntity.setUpdatedAt(currentDate);
        proceduresEntity.setId(id);
        session.update(proceduresEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void updateProcedure(ProceduresDto params) {
        Session session = HibernateSessionFactory.openSession();
        session.getTransaction().begin();
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, params.getId());
        proceduresEntity.setUpdatedAt(currentDate);
        proceduresEntity.setId(params.getId());
        proceduresEntity.setDescription(params.getDescription());
        session.update(proceduresEntity);
        session.getTransaction().commit();
        session.close();
    }
}
