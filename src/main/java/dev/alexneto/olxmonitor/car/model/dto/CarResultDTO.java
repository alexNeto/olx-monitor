package dev.alexneto.olxmonitor.car.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarResultDTO {
    private String title;
    private String image;
    private String price;
    private String description;
    private String internalId;
    private String url;

    private String model;
    private String brand;
    private String carType;
    private String year;
    private String mileage;
    private String motorPower;

    private String zipCode;
    private String city;
    private String neighborhood;
}
