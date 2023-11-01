package dev.alexneto.olxmonitor.home.model;

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
public class HomeResult {

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
    private String rooms;
    private String garageSize;
    private String zipCode;
    private String city;
    private String neighborhood;
    private String streetName;
}
