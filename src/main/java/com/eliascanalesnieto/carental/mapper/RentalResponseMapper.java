package com.eliascanalesnieto.carental.mapper;

import com.eliascanalesnieto.carental.dto.Rental;
import com.eliascanalesnieto.carental.dto.RentalResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RentalResponseMapper {

    public RentalResponse map(final List<Rental> rentals) {
        return new RentalResponse(rentals);
    }

}
