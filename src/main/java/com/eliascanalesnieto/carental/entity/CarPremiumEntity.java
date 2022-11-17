package com.eliascanalesnieto.carental.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = CarPremiumEntity.PREMIUM)
public class CarPremiumEntity extends CarModelEntity {

    public static final String PREMIUM = "PREMIUM";

    @Override
    public Float calculatePrice(final Long amountOfDaysRented) {
        return amountOfDaysRented * price;
    }

    @Override
    protected Float getPercentagePerExtraDay() {
        return 0.2f;
    }
}
