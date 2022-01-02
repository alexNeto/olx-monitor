package dev.alexneto.olxmonitor.car.scrappy;

import dev.alexneto.olxmonitor.car.model.CarResult;
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
public class CarDetailCollectorService {

    private static final String LOGTAG = "[CAR-DETAIL-COLLECTOR-SERVICE]";

    public CarResult getData(String url) {
        try {
            log.info("{} Collecting info from url={}", LOGTAG, url);
            Document document = Jsoup.connect(url).get();
            JSONObject json = new JSONObject(document.getElementById("initial-data").attr("data-json"));

            return CarResult.builder()
                    .title(getString(json, "subject"))
                    .image(getStemObject(json).getJSONArray("images").getJSONObject(0).getString("original"))
                    .price(getString(json, "priceValue"))
                    .description(getString(json, "body").replace("<br>", "\n"))
                    .internalId(getStemObject(json).get("listId").toString())
                    .url(getStemObject(json).getString("friendlyUrl"))
                    .model(getProperty(json, "vehicle_model"))
                    .brand(getProperty(json, "vehicle_brand"))
                    .carType(getProperty(json, "cartype"))
                    .year(getProperty(json, "regdate"))
                    .mileage(getProperty(json, "mileage"))
                    .motorPower(getProperty(json, "motorpower"))
                    .zipCode(getStemObject(json).getJSONObject("location").getString("zipcode"))
                    .city(getStemObject(json).getJSONObject("location").getString("municipality"))
                    .neighborhood(getStemObject(json).getJSONObject("location").getString("neighbourhood"))
                    .build();
        } catch (Exception e) {
            log.error("{} Error collecting info from url={}", LOGTAG, url, e);
            return new CarResult();
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
