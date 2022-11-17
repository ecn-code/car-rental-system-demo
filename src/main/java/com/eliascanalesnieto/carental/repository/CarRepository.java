package com.eliascanalesnieto.carental.repository;

import com.eliascanalesnieto.carental.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {

    CarEntity findByRegistrationNumber(final String registrationNumber);

}
