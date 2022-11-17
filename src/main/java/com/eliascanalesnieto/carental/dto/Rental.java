package com.eliascanalesnieto.carental.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rental {

    @ApiModelProperty(readOnly = true)
    private String id;

    @ApiModelProperty(example = "2022-06-14", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String rentedFrom;

    @ApiModelProperty(example = "2022-06-24", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String rentedTo;

    @ApiModelProperty(readOnly = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String deliveredCarDate;

    @ApiModelProperty(example = "1111PPP", required = true)
    private String carRegistrationNumber;

    @ApiModelProperty(example = "55555555Z", required = true)
    private String customerIdNumber;

    @ApiModelProperty(readOnly = true)
    private Float price;

    @ApiModelProperty(readOnly = true)
    private Float surcharges;

}
