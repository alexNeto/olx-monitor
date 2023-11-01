package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PageProps {
    public ArrayList<Ad> ads;
    public String category;
    public int selectedCategoryCode;
    public Filters filters;
    public int totalOfAds;
    public int pageIndex;
    public int pageSize;
    public String pageTitle;
    public String fullPageTitle;
    public String adLocation;
    public SearchBoxProps searchBoxProps;
    public DataContext dataContext;
    public ArrayList<Locations> locations;
    public ArrayList<NextLocation> nextLocations;
    public boolean hasModal;
    public ArrayList<Object> selectedNextLocations;
    public Object searchResultContext;
    public Location location;
    public String abTestGroups;
    public boolean isCategoryEligible;
    public SsrQuery ssrQuery;
    public String pageType;
}
