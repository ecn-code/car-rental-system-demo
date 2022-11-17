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
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Entity
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = CarModelEntity.TABLE_NAME)
public abstract class CarModelEntity {

    static final String TABLE_NAME = "CARMODELS";

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(insertable = false, updatable = false)
    private CarType type;

    protected Float price;

    private Integer loyaltyPoints;

    public Float calculateSurcharges(final Long daysExtra) {
        return daysExtra * (price + getExtraDayPrice());
    }

    protected Float getExtraDayPrice() {
        return price * getPercentagePerExtraDay();
    }

    public abstract Float calculatePrice(Long amountOfDaysRented);

    protected abstract Float getPercentagePerExtraDay();
}
