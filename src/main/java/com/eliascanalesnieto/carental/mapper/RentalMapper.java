package com.eliascanalesnieto.carental.mapper;


import com.eliascanalesnieto.carental.dto.Rental;
import com.eliascanalesnieto.carental.entity.RentalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RentalMapper {

    RentalEntity mapToEntity(final Rental rental);

    @Mapping(target = "carRegistrationNumber", source = "car.registrationNumber")
    Rental map(final RentalEntity rental);

}