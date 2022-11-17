package com.eliascanalesnieto.carental.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = CarBrandEntity.TABLE_NAME)
public class CarBrandEntity {

    static final String TABLE_NAME = "CARBRANDS";

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private Integer id;

    private String name;

    @ManyToOne
    private CarModelEntity model;

    public Float calculatePrice(final Long amountOfDaysRented) {
        return model.calculatePrice(amountOfDaysRented);
    }

    public Float calculateSurcharges(final Long daysExtra) {
        return model.calculateSurcharges(daysExtra);
    }
}
