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

        dataBaseController.getSessionFactory().getCurrentSession().close();

        List<Integer> ids = List.of(3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        long startRedis = System.currentTimeMillis();
        dataBaseController.testRedisData(ids);
        long stopRedis = System.currentTimeMillis();

        long startMysql = System.currentTimeMillis();
        dataBaseController.testMysqlData(ids);
        long stopMysql = System.currentTimeMillis();

        System.out.printf("%s:\t%d ms\n", "Redis", (stopRedis - startRedis));
        System.out.printf("%s:\t%d ms\n", "MySQL", (stopMysql - startMysql));


        dataBaseController.shutdown();
    }
}