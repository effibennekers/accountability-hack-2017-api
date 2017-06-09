package com.theaccountant.accountabilityhack.service;

import com.theaccountant.accountabilityhack.data.TestEntity;
import com.theaccountant.accountabilityhack.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DatabaseService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public TestEntity saveTestEntity() {
        TestEntity testEntity = new TestEntity();
        testEntity.setDate(new Date());
        saveEntity(testEntity);
        return testEntity;
    }

    private void saveEntity(Object testEntity) {
        Session session = sessionFactory.openSession();
        session.save(testEntity);
        Transaction tx = session.beginTransaction();
        tx.commit();
        session.close();
    }

}
