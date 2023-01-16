package com.javarush.delta.zazimko.dao;

import com.javarush.delta.zazimko.entity.City;
import com.javarush.delta.zazimko.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CityDAO extends UniversalDAO{
    public CityDAO(SessionFactory sessionFactory) {
        super(sessionFactory, City.class);
    }
    public int getTotalCount(){
        Session currentSession = getCurrentSession();
        Query<Long> query = currentSession.createQuery("select count (c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }
    public List<City> fetchData(CountryDAO countryDAO){
        try(Session currentSession = getCurrentSession()){
            List<City> allCities=new ArrayList<>();
            currentSession.beginTransaction();
            List<Country> countryDAOAll = countryDAO.getAll();

            int totalCount = getTotalCount();
            int step=500;
            for (int i = 0; i <totalCount  ; i+=step) {
                allCities.addAll(getItems(i,step));
            }
            currentSession.getTransaction().commit();
            return allCities;
        }
    }

}
