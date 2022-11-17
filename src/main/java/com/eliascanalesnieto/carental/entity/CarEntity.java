package com.eliascanalesnieto.carental.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = CarEntity.TABLE_NAME)
public class CarEntity {

    static final String TABLE_NAME = "CARS";

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private Integer id;

    @Column(unique = true)
    private String registrationNumber;

    @ManyToOne
    private CarBrandEntity brand;

    public Float calculatePrice(Long amountOfDaysRented) {
        return brand.calculatePrice(amountOfDaysRented);
    }

    public Float calculateSurcharges(final Long daysExtra) {
        return brand.calculateSurcharges(daysExtra);
    }

}
