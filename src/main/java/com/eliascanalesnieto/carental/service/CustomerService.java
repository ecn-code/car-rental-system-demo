package com.eliascanalesnieto.carental.service;

import com.eliascanalesnieto.carental.dto.Customer;

public interface CustomerService {

    Customer getByIdNumber(String idNumber);
}
