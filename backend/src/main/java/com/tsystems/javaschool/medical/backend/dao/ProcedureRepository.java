package com.tsystems.javaschool.medical.backend.dao;

import com.tsystems.javaschool.medical.backend.dto.ProceduresDto;
import com.tsystems.javaschool.medical.backend.entities.ProceduresEntity;
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
public class ProcedureRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public List<ProceduresEntity> getAll() {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProceduresEntity.class);
        criteria.addOrder(Order.asc("description"));
        criteria.add(Restrictions.eq("deleted", "N"));
        List<ProceduresEntity> proceduresEntities = criteria.list();

        return proceduresEntities;
    }

    @Transactional
    public void create(String description) {
        ProceduresEntity proceduresEntity = new ProceduresEntity();
        proceduresEntity.setDescription(description);
        proceduresEntity.setCreatedAt(getCurrentTimestamp());
        proceduresEntity.setUpdatedAt(getCurrentTimestamp());
        proceduresEntity.setDeleted("N");

        Session session = sessionFactory.getCurrentSession();
        session.persist(proceduresEntity);
    }

    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, id);
        proceduresEntity.setDeleted("Y");
        proceduresEntity.setUpdatedAt(getCurrentTimestamp());
        proceduresEntity.setId(id);
        session.update(proceduresEntity);
    }

    @Transactional
    public void update(ProceduresDto params) {
        Session session = sessionFactory.getCurrentSession();
        ProceduresEntity proceduresEntity = session.load(ProceduresEntity.class, params.getId());
        proceduresEntity.setUpdatedAt(getCurrentTimestamp());
        proceduresEntity.setId(params.getId());
        proceduresEntity.setDescription(params.getDescription());
        session.update(proceduresEntity);
    }

    @Transactional
    public ProceduresEntity getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(ProceduresEntity.class);
        criteria.add(Restrictions.eq("id", id));
        ProceduresEntity result = (ProceduresEntity) criteria.uniqueResult();
        return result;
    }

}
