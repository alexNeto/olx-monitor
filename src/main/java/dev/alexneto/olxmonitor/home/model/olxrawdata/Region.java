package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

@Data
public class Region {
    public String regionId;
    public String name;
    public RealName real_name;
    public String state;
    public Zone zone;
    public String friendlyName;
}
