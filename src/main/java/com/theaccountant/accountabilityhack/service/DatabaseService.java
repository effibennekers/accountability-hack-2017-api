package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DatabaseService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public <T> T save(T entity) {
        Session session = sessionFactory.openSession();
        session.save(entity);
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        return entity;
    }

    public <T> List<T> getAll(Class<T> clazz, Integer start, Integer maxrows) {
        Session session = sessionFactory.openSession();
        CriteriaQuery<T> criteria = session.getCriteriaBuilder().createQuery(clazz);
        Root<T> root = criteria.from(clazz);
        CriteriaQuery<T> select = criteria.select(root);
        //criteria.where(3)( builder.equal( personRoot.get( Person_.eyeColor ), "brown" ) );
        Query<T> query = session.createQuery(select);
        query.setFirstResult(start);
        query.setMaxResults(maxrows);

        List<T> resultList = query.getResultList();
        session.close();
        return resultList;
    }

}
