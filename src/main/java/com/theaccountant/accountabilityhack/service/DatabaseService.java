package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private <T> T save(T entity) {
        Session session = sessionFactory.openSession();
        session.save(entity);
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
        return entity;
    }

}
