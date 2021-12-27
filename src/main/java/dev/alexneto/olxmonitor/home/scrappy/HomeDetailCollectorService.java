package dev.alexneto.olxmonitor.home.scrappy;

import dev.alexneto.olxmonitor.home.model.HomeResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HomeDetailCollectorService {

    private static final String LOGTAG = "[HOME-DETAIL-COLLECTOR-SERVICE]";

    public HomeResult getData(String url) {
        try {
            log.info("{} Collecting info from url={}", LOGTAG, url);
            Document document = Jsoup.connect(url).get();
            JSONObject json = new JSONObject(document.getElementById("initial-data").attr("data-json"));

            return HomeResult.builder()
                    .title(getString(json, "subject"))
                    .image(getStemObject(json).getJSONArray("images").getJSONObject(0).getString("original"))
                    .price(getString(json, "priceValue"))
                    .description(getString(json, "body").replace("<br>", "\n"))
                    .internalId(getStemObject(json).get("listId").toString())
                    .url(getStemObject(json).getString("friendlyUrl"))
                    .rooms(getProperty(json, "rooms"))
                    .garageSize(getProperty(json, "garage_spaces"))
                    .zipCode(getStemObject(json).getJSONObject("location").getString("zipcode"))
                    .city(getStemObject(json).getJSONObject("location").getString("municipality"))
                    .neighborhood(getStemObject(json).getJSONObject("location").getString("neighbourhood"))
                    .streetName(getStemObject(json).getJSONObject("location").get("address").toString())
                    .build();
        } catch (Exception e) {
            log.error("{} Error collecting info from url={}", LOGTAG, url, e);
            return new HomeResult();
        }
    }

    private String getString(JSONObject json, String key) {
        return getStemObject(json).getString(key);
    }

    private JSONObject getStemObject(JSONObject json) {
        return json.getJSONObject("ad");
    }

    private String getProperty(JSONObject json, String property) {
        return json.getJSONObject("ad")
                .getJSONArray("properties")
                .toList().stream()
                .map(i -> (Map) i)
                .filter(i -> i.get("name").equals(property))
                .map(i -> i.get("value"))
                .findFirst().orElse("").toString();
    }
}
