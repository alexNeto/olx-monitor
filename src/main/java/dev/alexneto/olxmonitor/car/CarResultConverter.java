package dev.alexneto.olxmonitor.car;

import dev.alexneto.olxmonitor.car.model.CarResult;
import dev.alexneto.olxmonitor.car.model.dto.CarResultDTO;

public final class CarResultConverter {

    public static CarResultDTO toDTO(CarResult carResult) {
        return CarResultDTO.builder()
                .title(carResult.getTitle())
                .image(carResult.getImage())
                .price(carResult.getPrice())
                .description(carResult.getDescription())
                .internalId(carResult.getInternalId())
                .url(carResult.getUrl())
                .model(carResult.getModel())
                .brand(carResult.getBrand())
                .carType(carResult.getCarType())
                .year(carResult.getYear())
                .mileage(carResult.getMileage())
                .motorPower(carResult.getMotorPower())
                .zipCode(carResult.getZipCode())
                .city(carResult.getCity())
                .neighborhood(carResult.getNeighborhood())
                .build();
    }

}
