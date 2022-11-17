package com.eliascanalesnieto.carental.mapper;

import com.eliascanalesnieto.carental.dto.Customer;
import com.eliascanalesnieto.carental.entity.CustomerEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer map(final CustomerEntity customer);

}