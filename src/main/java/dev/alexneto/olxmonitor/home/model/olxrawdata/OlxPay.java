package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OlxPay {
    public boolean enabled;
    public ArrayList<Object> dynamicBadgeProps;
    public ArrayList<Object> installments;
    public boolean isCategoryEligible;
}
