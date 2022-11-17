package com.eliascanalesnieto.carental;

import com.eliascanalesnieto.carental.dto.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarentalApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void givenSimulateRentals_thenReturnRentalsWithPrice() {
	    URI uri = UriComponentsBuilder.fromUriString("/rentals/simulate")
                .build()
                .toUri();


        final RentalResponse rentalResponse = testRestTemplate.postForObject(uri,
                List.of(
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111PPP")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-19")
                                .carRegistrationNumber("1111SUV")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-12")
                                .carRegistrationNumber("2111SUV")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111SSS")
                                .build()
                ), RentalResponse.class);
        Assertions.assertEquals(new RentalResponse(
                List.of(
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111PPP")
                                .price(3000f)
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-19")
                                .carRegistrationNumber("1111SUV")
                                .price(1290f)
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-12")
                                .carRegistrationNumber("2111SUV")
                                .price(300f)
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111SSS")
                                .price(440f)
                                .build()
                )
        ), rentalResponse);
    }

    @Test
    void givenRentals_thenTheyAreSavedWithPrice() {
        URI uri = UriComponentsBuilder.fromUriString("/rentals")
                .build()
                .toUri();


        final RentalResponse rentalResponse = testRestTemplate.postForObject(uri,
                List.of(
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111PPP")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-19")
                                .carRegistrationNumber("1111SUV")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-12")
                                .carRegistrationNumber("2111SUV")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111SSS")
                                .build()
                ), RentalResponse.class);

        RentalResponse savedRentals = testRestTemplate.getForObject(uri, RentalResponse.class);
        Assertions.assertEquals(List.of(
                Rental.builder()
                        .id("1")
                        .rentedFrom("2022-06-10")
                        .rentedTo("2022-06-20")
                        .carRegistrationNumber("1111PPP")
                        .price(3000f)
                        .build(),
                Rental.builder()
                        .id("2")
                        .rentedFrom("2022-06-10")
                        .rentedTo("2022-06-19")
                        .carRegistrationNumber("1111SUV")
                        .price(1290f)
                        .build(),
                Rental.builder()
                        .id("3")
                        .rentedFrom("2022-06-10")
                        .rentedTo("2022-06-12")
                        .carRegistrationNumber("2111SUV")
                        .price(300f)
                        .build(),
                Rental.builder()
                        .id("4")
                        .rentedFrom("2022-06-10")
                        .rentedTo("2022-06-20")
                        .carRegistrationNumber("1111SSS")
                        .price(440f)
                        .build()
        ), savedRentals.getRentals());
    }

    @Test
    void givenRentals_whenWeFinished_ThenRentalsCalculateSurchargeAndCustomerHaveLoyaltyPoints() {
        URI rentalsUri = UriComponentsBuilder.fromUriString("/rentals")
                .build()
                .toUri();


        final RentalResponse rentalResponse = testRestTemplate.postForObject(rentalsUri,
                List.of(
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("1111PPP")
                                .customerIdNumber("11111111Z")
                                .build(),
                        Rental.builder()
                                .rentedFrom("2022-06-10")
                                .rentedTo("2022-06-20")
                                .carRegistrationNumber("2111SUV")
                                .customerIdNumber("21111111Z")
                                .build()
                ), RentalResponse.class);

        String premiumId = rentalResponse.getRentals().get(0).getId();
        String suvId = rentalResponse.getRentals().get(1).getId();

        UriComponentsBuilder finishRentalUri = UriComponentsBuilder.fromUriString("/rentals/{id}/finish");

        Rental premiumRental = testRestTemplate.postForObject(finishRentalUri.build(Map.of("id", premiumId)), "2022-06-22", Rental.class);
        Rental suvRental = testRestTemplate.postForObject(finishRentalUri.build(Map.of("id", suvId)), "2022-06-21", Rental.class);

        Assertions.assertEquals(720f, premiumRental.getSurcharges());
        Assertions.assertEquals(180f, suvRental.getSurcharges());

        UriComponentsBuilder customerUri = UriComponentsBuilder.fromUriString("/customers/{idNumber}");
        Customer customerPremium = testRestTemplate.getForObject(customerUri.build(Map.of("idNumber", "11111111Z")), Customer.class);
        Customer customerSuv = testRestTemplate.getForObject(customerUri.build(Map.of("idNumber", "21111111Z")), Customer.class);

        Assertions.assertEquals(60, customerPremium.getLoyaltyPoints());
        Assertions.assertEquals(33, customerSuv.getLoyaltyPoints());
    }

}
