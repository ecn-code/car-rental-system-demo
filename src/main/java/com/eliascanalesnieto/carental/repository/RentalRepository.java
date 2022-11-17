package com.eliascanalesnieto.carental.repository;

import com.eliascanalesnieto.carental.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {

}
