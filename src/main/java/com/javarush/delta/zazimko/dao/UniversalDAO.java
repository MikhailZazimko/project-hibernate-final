package com.javarush.delta.zazimko.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class UniversalDAO<T> {
    private final SessionFactory sessionFactory;
    private final Class<T> clazz;

    public UniversalDAO(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public T getById(int id) {
        return getCurrentSession().get(clazz, id);
    }

    public List<T> getAll() {
        Session currentSession = getCurrentSession();
        Query<T> query = currentSession.createQuery("from " + clazz.getName(), clazz);
        return query.list();
    }

    public List<T> getItems(int offset, int limit) {
        Query<T> query = getCurrentSession()
                .createQuery("from " + clazz.getName(), clazz);

        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }
@SuppressWarnings("all")
    public T save(T t) {
        try (Session currentSession = getCurrentSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.saveOrUpdate(t);
            transaction.commit();
            return t;
        }
    }
    @SuppressWarnings("all")
    public T update(T t) {
        try (Session currentSession = getCurrentSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.merge(t);
            transaction.commit();
            return t;
        }
    }
    @SuppressWarnings("all")
    public void delete(T t) {
        try (Session currentSession = getCurrentSession()) {
            Transaction transaction = currentSession.beginTransaction();
            currentSession.remove(t);
            transaction.commit();
        }
    }
    @SuppressWarnings("all")
    public void deleteById(int id) {
        try (Session currentSession = getCurrentSession()) {
            Transaction transaction = currentSession.beginTransaction();
            T t = currentSession.get(clazz, id);
            currentSession.remove(t);
            transaction.commit();
        }
    }
}
