package com.eliascanalesnieto.carental.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = CarSuvEntity.SUV)
public class CarSuvEntity extends CarModelEntity {

    public static final String SUV = "SUV";

    @Column(insertable = false, updatable = false)
    @Formula(value = "(select min(cm.price) from CARMODELS cm where cm.type = '" + CarSmallEntity.SMALL + "')")
    private Float smallPrice;

    @Override
    public Float calculatePrice(Long amountOfDaysRented) {
        Long leftDays = amountOfDaysRented;
        Float priceCalculated = 0f;

        if(leftDays > 30) {
            priceCalculated = (leftDays - 30) * price * 0.5f;
            leftDays = 30l;
        }

        if(leftDays > 7) {
            priceCalculated += (leftDays - 7) * price * 0.8f;
            leftDays = 7l;
        }

        return priceCalculated + leftDays * price;
    }

    @Override
    protected Float getExtraDayPrice() {
        return smallPrice * getPercentagePerExtraDay();
    }

    @Override
    protected Float getPercentagePerExtraDay() {
        return 0.6f;
    }
}
