package dev.alexneto.olxmonitor.home.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HomeResultDTO {
    private String title;
    private String image;
    private String price;
    private String description;
    private String internalId;
    private String url;
    private String rooms;
    private String garageSize;
    private String zipCode;
    private String city;
    private String neighborhood;
    private String streetName;
}
