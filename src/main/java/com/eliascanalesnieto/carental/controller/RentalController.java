package com.eliascanalesnieto.carental.controller;

import com.eliascanalesnieto.carental.dto.Rental;
import com.eliascanalesnieto.carental.dto.RentalResponse;
import com.eliascanalesnieto.carental.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class RentalController implements RentalApi {

    private static final Logger log = LoggerFactory.getLogger(RentalController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private RentalService rentalService;

    public ResponseEntity<Void> deleteRental(@Parameter(in = ParameterIn.PATH, description = "id of rental", required=true, schema=@Schema()) @PathVariable("id") String id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public Rental getRentalById(@Parameter(in = ParameterIn.PATH, description = "id of rental", required=true, schema=@Schema()) @PathVariable("id") String id) {
        return rentalService.getRent(id);
    }

    @Override
    public Rental finishRent(final String id, final String deliveredCarDate) {
        return rentalService.finishRent(id, deliveredCarDate);
    }

    public RentalResponse getRentals() {
        return rentalService.getRentals();
    }

    public RentalResponse rent(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody List<Rental> rentals) {
        return rentalService.rent(rentals);
    }

    public RentalResponse simulateRent(@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody List<Rental> rentals) {
        return rentalService.simulate(rentals);
    }

    public ResponseEntity<Rental> updateRentalById(@Parameter(in = ParameterIn.PATH, description = "id of rental", required=true, schema=@Schema()) @PathVariable("id") String id,@Parameter(in = ParameterIn.DEFAULT, description = "", schema=@Schema()) @Valid @RequestBody Rental body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Rental>(objectMapper.readValue("{\n  \"start_from\" : \"2000-01-23\",\n  \"car\" : {\n    \"registrationNumber\" : \"1221ddd\",\n    \"model\" : {\n      \"name\" : \"bmw x5\",\n      \"id\" : 1,\n      \"type\" : \"premium\"\n    },\n    \"id\" : 10\n  },\n  \"delivered_car\" : \"2000-01-23\",\n  \"end_to\" : \"2000-01-23\",\n  \"id\" : 1,\n  \"customer\" : {\n    \"address\" : [ {\n      \"zip\" : \"94301\",\n      \"city\" : \"Palo Alto\",\n      \"street\" : \"437 Lytton\",\n      \"state\" : \"CA\"\n    }, {\n      \"zip\" : \"94301\",\n      \"city\" : \"Palo Alto\",\n      \"street\" : \"437 Lytton\",\n      \"state\" : \"CA\"\n    } ],\n    \"id\" : 100000,\n    \"username\" : \"fehguy\"\n  }\n}", Rental.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Rental>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Rental>(HttpStatus.NOT_IMPLEMENTED);
    }

}
