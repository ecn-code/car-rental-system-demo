package com.eliascanalesnieto.carental.controller;

import com.eliascanalesnieto.carental.dto.Customer;
import com.eliascanalesnieto.carental.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private CustomerService customerService;


    @Override
    public Customer getCustomerById(String idNumber) {
        return customerService.getByIdNumber(idNumber);
    }
}
