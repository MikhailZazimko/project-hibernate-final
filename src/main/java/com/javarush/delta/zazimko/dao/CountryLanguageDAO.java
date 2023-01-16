package com.javarush.delta.zazimko.dao;

import com.javarush.delta.zazimko.entity.CountryLanguage;
import org.hibernate.SessionFactory;

public class CountryLanguageDAO extends UniversalDAO{
    public CountryLanguageDAO(SessionFactory sessionFactory) {
        super(sessionFactory, CountryLanguage.class);
    }
}
