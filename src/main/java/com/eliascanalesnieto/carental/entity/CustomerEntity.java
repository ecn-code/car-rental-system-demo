package com.eliascanalesnieto.carental.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = CustomerEntity.TABLE_NAME)
public class CustomerEntity {

    static final String TABLE_NAME = "CUSTOMERS";

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private Integer id;

    @Column(unique = true)
    private String idNumber;

    @Column(insertable = false, updatable = false)
    @Formula(value = "(select sum(r.loyalty_points) from RENTALS r where r.customer_id = id)")
    private Integer loyaltyPoints;

}
