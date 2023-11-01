package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

@Data
public class VehicleReport {
    public boolean enabled;
    public Object title;
    public Object description;
    public Object reportLink;
    public Object reportTitle;
    public Object tags;
}
