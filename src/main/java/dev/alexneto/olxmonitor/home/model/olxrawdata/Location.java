package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Location {
    public String canonicalUrl;
    public ArrayList<State> states;
    public ArrayList<Region> regions;
    public ArrayList<Object> zones;
    public ArrayList<Object> municipalities;
    public String url;
    public String mobileFilterUrl;
    public State state;
}