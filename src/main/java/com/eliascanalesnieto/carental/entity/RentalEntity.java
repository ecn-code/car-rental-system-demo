package com.eliascanalesnieto.carental.entity;

import com.eliascanalesnieto.carental.exception.BadRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = RentalEntity.TABLE_NAME)
public class RentalEntity {

    static final String TABLE_NAME = "RENTALS";

    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private Integer id;

    private Float price;

    private Float surcharges;

    private String rentedFrom;

    private String rentedTo;

    private String deliveredCarDate;

    private Long loyaltyPoints;

    @ManyToOne
    private CarEntity car;

    @ManyToOne
    private CustomerEntity customer;

    public void calculatePrice() {
        final Long daysRented = calculateAmountOfDaysRented();
        this.price = car.calculatePrice(daysRented);
    }

    public void calculateSurcharges() {
        final Long daysExtra = calculateAmountOfExtraDays();
        this.surcharges = car.calculateSurcharges(daysExtra);
    }

    public void calculateLoyaltyPoints() {
        loyaltyPoints = car.getBrand().getModel().getLoyaltyPoints() * calculateDaysDistance(rentedFrom, deliveredCarDate);
    }

    private Long calculateAmountOfDaysRented() {
        return calculateDaysDistance(rentedFrom, rentedTo);
    }

    private Long calculateAmountOfExtraDays() {
        return calculateDaysDistance(rentedTo, deliveredCarDate);
    }

    private Long calculateDaysDistance(final String dateFrom, final String dateTo) {

        if(Strings.isBlank(dateFrom) || Strings.isBlank(dateTo)) {
            return 0l;
        }

        final Date from;
        final Date to;
        try {
            from = dateFormat.parse(dateFrom);
            to = dateFormat.parse(dateTo);
        } catch (ParseException e) {
            throw new BadRequest();
        }

        long diffInMillies = Math.abs(from.getTime() - to.getTime());
        long days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return days;
    }

}
