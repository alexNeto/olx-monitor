package dev.alexneto.olxmonitor.home;

import dev.alexneto.olxmonitor.home.model.HomeResult;
import dev.alexneto.olxmonitor.home.model.dto.HomeResultDTO;

public final class HomeResultConverter {

    public static HomeResultDTO toDTO(HomeResult homeResult) {
        return HomeResultDTO.builder()
                .title(homeResult.getTitle())
                .image(homeResult.getImage())
                .price(homeResult.getPrice())
                .description(homeResult.getDescription())
                .internalId(homeResult.getInternalId())
                .url(homeResult.getUrl())
                .rooms(homeResult.getRooms())
                .garageSize(homeResult.getGarageSize())
                .zipCode(homeResult.getZipCode())
                .city(homeResult.getCity())
                .neighborhood(homeResult.getNeighborhood())
                .streetName(homeResult.getStreetName())
                .build();
    }
}
