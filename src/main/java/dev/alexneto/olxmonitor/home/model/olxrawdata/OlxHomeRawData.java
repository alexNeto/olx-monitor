package dev.alexneto.olxmonitor.home.model.olxrawdata;

import lombok.Data;

import java.util.ArrayList;

@Data
public class OlxHomeRawData{
    public Props props;
    public String page;
    public Query query;
    public String buildId;
    public String assetPrefix;
    public boolean isFallback;
    public ArrayList<Integer> dynamicIds;
    public boolean gssp;
    public String locale;
    public ArrayList<String> locales;
    public String defaultLocale;
    public ArrayList<ScriptLoader> scriptLoader;
}

