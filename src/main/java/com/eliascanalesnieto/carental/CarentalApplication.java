package com.eliascanalesnieto.carental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.TimeZone;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {
		"com.eliascanalesnieto.carental.controller",
		"com.eliascanalesnieto.carental.service",
		"com.eliascanalesnieto.carental.repository",
		"com.eliascanalesnieto.carental.mapper",
        "com.eliascanalesnieto.carental.exception",
})
public class CarentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarentalApplication.class, args);
	}

}
