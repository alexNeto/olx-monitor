package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class NextLocation {
    public int rows;
    public ArrayList<Locations> locations;
}
