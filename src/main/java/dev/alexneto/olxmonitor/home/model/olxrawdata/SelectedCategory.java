package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

@Data
public class SelectedCategory {
    public String value;
    public String breadcrumb_name;
    public String leaf;
    public String level;
    public String min_location;
    public String name;
    public String parent;
    public String friendlyName;
}
