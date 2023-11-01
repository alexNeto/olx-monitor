package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

@Data
public class State {
    public String stateId;
    public String coords;
    public String default_region;
    public String fullsitemap;
    public String name;
    public String preposition;
    public String single_region;
    public String symbol;
    public String friendlyName;
    public String prefixedFriendlyName;
}
