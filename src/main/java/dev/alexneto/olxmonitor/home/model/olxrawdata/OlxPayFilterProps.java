package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OlxPayFilterProps {
    public boolean isCategoryEligible;
    public ArrayList<Object> selectedPayOptions;
}
