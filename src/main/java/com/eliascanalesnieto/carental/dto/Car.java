package com.eliascanalesnieto.carental.dto;

import com.eliascanalesnieto.carental.entity.CarType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private CarType type;

    private String registrationNumber;

}
