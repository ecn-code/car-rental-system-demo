package com.eliascanalesnieto.carental.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = CarSmallEntity.SMALL)
public class CarSmallEntity extends CarModelEntity {

    public static final String SMALL = "SMALL";

    @Override
    public Float calculatePrice(final Long amountOfDaysRented) {
        if(amountOfDaysRented > 7) {
            return 7 * price + (amountOfDaysRented - 7) * price * 0.6f;
        }

        return amountOfDaysRented * price;
    }

    @Override
    protected Float getPercentagePerExtraDay() {
        return 0.3f;
    }
}
