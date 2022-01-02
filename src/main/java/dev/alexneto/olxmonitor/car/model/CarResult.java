package dev.alexneto.olxmonitor.car.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = @Index(columnList = "olxMonitorId"))
public class CarResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long olxMonitorId;
    private String title;
    private String image;
    private String price;
    @Column(columnDefinition = "TEXT")
    private String description;

    /**
     * Id used by olx to track ads
     */
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
