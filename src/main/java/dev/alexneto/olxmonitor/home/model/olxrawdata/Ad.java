package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Ad {
    public String subject;
    public String title;
    public String price;
    public int listId;
    public String lastBumpAgeSecs;
    public String oldPrice;
    public boolean professionalAd;
    public boolean isFeatured;
    public String listingCategoryId;
    public ArrayList<Image> images;
    public int videoCount;
    public boolean isChatEnabled;
    public boolean fixedOnTop;
    public String url;
    public String thumbnail;
    public int date;
    public int imageCount;
    public String location;
    public LocationDetails locationDetails;
    public String category;
    public int searchCategoryLevelZero;
    public int searchCategoryLevelOne;
    public ArrayList<Property> properties;
    public AccountActivityStatus accountActivityStatus;
    public int position;
    public OlxPay olxPay;
    public boolean olxPayBadgeEnabled;
    public OlxDelivery olxDelivery;
    public boolean olxDeliveryBadgeEnabled;
    public boolean installments;
    public VehicleReport vehicleReport;
    public ArrayList<Object> vehicleTags;
    public Object vehiclePills;
    public boolean isFavorited;
    public boolean hasRealEstateHighlight;
    public ArrayList<TrackingSpecificDatum> trackingSpecificData;
    public Object carSpecificData;
    public String advertisingId;
    public String deviceType;
    public boolean hasFilter;
}
