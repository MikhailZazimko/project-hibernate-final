package com.javarush.delta.zazimko.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "city", schema = "world")
@AllArgsConstructor
@NoArgsConstructor

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @Column(name = "district")
    private String district;
    @Column(name = "population")
    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
