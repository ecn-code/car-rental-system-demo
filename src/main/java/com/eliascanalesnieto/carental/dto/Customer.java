package com.eliascanalesnieto.carental.dto;

import com.eliascanalesnieto.carental.entity.CarType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private String idNumber;

    private Integer loyaltyPoints;

}
