package com.javarush.delta.zazimko;

import com.javarush.delta.zazimko.conroller.DataBaseController;
import com.javarush.delta.zazimko.entity.City;
import com.javarush.delta.zazimko.redis.CityCountry;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataBaseController dataBaseController = new DataBaseController();
        List<City> cities = dataBaseController.getCityDAO().fetchData(dataBaseController.getCountryDAO());
        List<CityCountry> preparedData = dataBaseController.transformData(cities);
        dataBaseController.pushToRedis(preparedData);
        dataBaseController.shutdown();
    }
}