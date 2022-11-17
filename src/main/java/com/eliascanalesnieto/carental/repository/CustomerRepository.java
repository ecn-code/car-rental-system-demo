package com.eliascanalesnieto.carental.repository;

import com.eliascanalesnieto.carental.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    CustomerEntity findByIdNumber(final String idNumber);

}
