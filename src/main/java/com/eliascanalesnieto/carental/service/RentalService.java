package com.eliascanalesnieto.carental.service;

import com.eliascanalesnieto.carental.dto.Rental;
import com.eliascanalesnieto.carental.dto.RentalResponse;

import java.util.List;

public interface RentalService {

     RentalResponse simulate(final List<Rental> rentals);

     RentalResponse rent(final List<Rental> rentals);

     RentalResponse getRentals();

    Rental finishRent(final String id, final String deliveredCarDate);

    Rental getRent(String id);
}
