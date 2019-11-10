package com.tsystems.javaschool.medical.backend.services;

import com.tsystems.javaschool.medical.backend.dto.ProceduresDto;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
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
public class ProceduresService {
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private SessionFactory sessionFactory;

    public List<ProceduresDto> getProceduresList() {

        Session session = sessionFactory.openSession();
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
        proceduresEntity.setCreatedAt(getCurrentTimestamp());
        proceduresEntity.setUpdatedAt(getCurrentTimestamp());
        proceduresEntity.setDeleted("N");
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(proceduresEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteProcedure(int id) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, id);
        proceduresEntity.setDeleted("Y");
        proceduresEntity.setUpdatedAt(getCurrentTimestamp());
        proceduresEntity.setId(id);
        session.update(proceduresEntity);
        session.getTransaction().commit();
        session.close();
    }

    public void updateProcedure(ProceduresDto params) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, params.getId());
        proceduresEntity.setUpdatedAt(getCurrentTimestamp());
        proceduresEntity.setId(params.getId());
        proceduresEntity.setDescription(params.getDescription());
        session.update(proceduresEntity);
        session.getTransaction().commit();
        session.close();
    }
}
