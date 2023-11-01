package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Query {
    public String ros;
    public ArrayList<String> route;
}
