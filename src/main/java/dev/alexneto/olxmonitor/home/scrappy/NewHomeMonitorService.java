package dev.alexneto.olxmonitor.home.scrappy;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexneto.olxmonitor.home.HomeResultRepository;
import dev.alexneto.olxmonitor.home.model.HomeResult;
import dev.alexneto.olxmonitor.home.model.olxrawdata.Ad;
import dev.alexneto.olxmonitor.home.model.olxrawdata.OlxHomeRawData;
import dev.alexneto.olxmonitor.home.model.olxrawdata.Property;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NewHomeMonitorService {

    private final HomeResultRepository monitorResultRepository;

    private static final String LOGTAG = "[NEW-HOME-MONITOR-SERVICE]";

    public List<String> verifyNewItems(String url) {
        try {
            log.info("{} Starting data collection from url={}", LOGTAG, url);

            Document document = Jsoup.connect(url).get();
            String jsonAsString = document.getElementById("__NEXT_DATA__").childNode(0).toString();

            List<HomeResult> homeResultList = new ObjectMapper().readValue(jsonAsString, OlxHomeRawData.class)
                    .getProps().getPageProps().getAds()
                    .stream().filter(Objects::nonNull)
                    .map(this::toHomeResult)
                    .collect(Collectors.toList());

            return filterNotSaved(homeResultList);

        } catch (IOException e) {
            log.error("{} Error getting info from url={}", LOGTAG, url, e);
        }
        return new ArrayList<>();
    }

    private HomeResult toHomeResult(Ad ad) {
        HomeResult homeResult = new HomeResult();
        homeResult.setTitle(ad.getTitle());
        homeResult.setImage(ad.getThumbnail());
        homeResult.setPrice(ad.getPrice());
        homeResult.setDescription(ad.getSubject());
        homeResult.setInternalId(Integer.toString(ad.getListId()));
        homeResult.setUrl(ad.getUrl());
        homeResult.setRooms(getProperty(ad, "rooms"));
        homeResult.setGarageSize(getProperty(ad, "garage_spaces"));
        return homeResult;
    }

    private static String getProperty(Ad ad, String property) {
        String defaultValue = "N/A";
        if (ad == null || ad.getProperties() == null || property == null) return defaultValue;
        return ad.getProperties()
                .stream()
                .filter(p -> property.equalsIgnoreCase(p.name))
                .map(Property::getValue)
                .findFirst().orElse(defaultValue);
    }

    private List<String> filterNotSaved(List<HomeResult> nodes) {
        return nodes
                .stream()
                .filter(this::isNotSaved)
                .map(HomeResult::getUrl)
                .filter(Objects::nonNull)
                .filter(i -> !"".equals(i))
                .collect(Collectors.toList());
    }

    private Elements getContentElement(Node node) {
        return ((Element) node).getElementsByAttribute("data-lurker_list_id");
    }

    @Cacheable(cacheNames = "internal-home-id-is-not-saved")
    public boolean isNotSaved(HomeResult node) {
        return !monitorResultRepository.existsByInternalId(getInternalId(node));
    }

    private String getInternalId(HomeResult node) {
        return node.getInternalId();
    }
}
