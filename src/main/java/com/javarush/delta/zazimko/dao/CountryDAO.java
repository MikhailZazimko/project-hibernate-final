package com.javarush.delta.zazimko.dao;

import com.javarush.delta.zazimko.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDAO extends UniversalDAO{
    public CountryDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Country.class);
    }

    @Override
    public List<Country> getAll() {
        Session currentSession = getCurrentSession();
        Query<Country> query = currentSession.createQuery("select c from Country c join fetch c.languages"
                , Country.class);
        return query.list();
    }
}
