package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Filters {
    public Rooms rooms;
    public String realestatetype_1001;
    public String selectedCategoryCode;
    public String state;
    public String sorting;
    public ArrayList<String> selectedAdTypes;
    public int pageIndex;
    public String category;
}
