package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

@Data
public class Locations {
    public String value;
    public String level;
    public String label;
    public String url;
    public int count;
}
