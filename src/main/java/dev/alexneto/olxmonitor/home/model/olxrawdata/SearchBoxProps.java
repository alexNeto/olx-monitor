package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SearchBoxProps {
    public String keyword;
    public int mode;
    public ArrayList<Object> categories;
    public SelectedCategory selectedCategory;
    public ArrayList<SearchLink> searchLinks;
    public ArrayList<Object> filtersUI;
    public ArrayList<Object> customFiltersConfig;
    public String activatedCustomFilter;
    public OlxPayFilterProps olxPayFilterProps;
    public String deviceType;
}
