package com.eliascanalesnieto.carental.service;

import com.eliascanalesnieto.carental.dto.Customer;
import com.eliascanalesnieto.carental.dto.Rental;
import com.eliascanalesnieto.carental.dto.RentalResponse;
import com.eliascanalesnieto.carental.entity.CarEntity;
import com.eliascanalesnieto.carental.entity.CustomerEntity;
import com.eliascanalesnieto.carental.entity.RentalEntity;
import com.eliascanalesnieto.carental.exception.BadRequest;
import com.eliascanalesnieto.carental.exception.NotFound;
import com.eliascanalesnieto.carental.mapper.CarMapper;
import com.eliascanalesnieto.carental.mapper.CustomerMapper;
import com.eliascanalesnieto.carental.mapper.RentalMapper;
import com.eliascanalesnieto.carental.repository.CarRepository;
import com.eliascanalesnieto.carental.repository.CustomerRepository;
import com.eliascanalesnieto.carental.repository.RentalRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;

    private final CarRepository carRepository;

    private final CustomerRepository customerRepository;

    private final CarMapper carMapper;

    private final CustomerMapper customerMapper;

    private final RentalMapper rentalMapper;

    @Override
    public RentalResponse simulate(final List<Rental> rentals) {
        return new RentalResponse(rentals.stream()
                .map(this::simulate)
                .map(rentalMapper::map)
                .collect(Collectors.toList()));
    }

    @Override
    public RentalResponse rent(List<Rental> rentals) {
        RentalResponse rentalsWithPrice = new RentalResponse(rentals.stream()
                .map(this::simulate)
                .map(rentalEntity -> rentalRepository.save(rentalEntity))
                .map(rentalMapper::map)
                .collect(Collectors.toList()));

        return rentalsWithPrice;
    }

    @Override
    public RentalResponse getRentals() {
        return new RentalResponse(CollectionUtils.emptyIfNull(rentalRepository.findAll()).stream()
                .map(rentalMapper::map).collect(Collectors.toList()));
    }

    @Override
    public Rental finishRent(final String id, final String deliveredCarDate) {
        RentalEntity rentalEntity = rentalRepository.findById(Integer.parseInt(id))
                .orElseThrow(NotFound::new);
        rentalEntity.setDeliveredCarDate(deliveredCarDate);
        rentalEntity.calculatePrice();
        rentalEntity.calculateSurcharges();
        rentalEntity.calculateLoyaltyPoints();
        rentalRepository.save(rentalEntity);

        return rentalMapper.map(rentalEntity);
    }

    @Override
    public Rental getRent(String id) {
        return rentalMapper.map(rentalRepository.findById(Integer.parseInt(id)).orElseThrow(NotFound::new));
    }

    private RentalEntity simulate(final Rental rentalRequested) {
        final RentalEntity rental = rentalMapper.mapToEntity(rentalRequested);
        final CarEntity car = carRepository.findByRegistrationNumber(rentalRequested.getCarRegistrationNumber());
        final CustomerEntity customerEntity = customerRepository.findByIdNumber(rentalRequested.getCustomerIdNumber());

        if(car == null) {
            throw new BadRequest();
        }

        rental.setCar(car);
        rental.setCustomer(customerEntity);
        rental.calculatePrice();

        return rental;
    }
}
