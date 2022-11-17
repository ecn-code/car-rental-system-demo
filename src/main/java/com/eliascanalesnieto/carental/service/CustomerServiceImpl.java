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
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public Customer getByIdNumber(final String idNumber) {
        return customerMapper.map(customerRepository.findByIdNumber(idNumber));
    }
}
