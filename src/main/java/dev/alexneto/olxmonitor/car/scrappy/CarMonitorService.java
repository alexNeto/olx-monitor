package dev.alexneto.olxmonitor.car.scrappy;

import dev.alexneto.olxmonitor.car.CarResultRepository;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarMonitorService {

    private final CarResultRepository monitorResultRepository;

    private static final String LOGTAG = "[NEW-CAR-MONITOR-SERVICE]";

    public List<String> verifyNewItems(String url) {
        try {
            log.info("{} Starting data collection from url={}", LOGTAG, url);
            Document document = Jsoup.connect(url).get();

            Element adListContainer = document.getElementById("ad-list");
            if (adListContainer != null) {
                return filterNotSaved(adListContainer.childNodes());
            }
        } catch (IOException e) {
            log.error("{} Error getting info from url={}", LOGTAG, url, e);
        }
        return new ArrayList<>();
    }

    private List<String> filterNotSaved(List<Node> nodes) {
        return nodes
                .stream()
                .filter(this::isNotSaved)
                .map(this::getHref)
                .filter(i -> !"".equals(i))
                .collect(Collectors.toList());
    }

    private Elements getContentElement(Node node) {
        return ((Element) node).getElementsByAttribute("data-lurker_list_id");
    }

    @Cacheable(cacheNames = "internal-car-id-is-not-saved")
    public boolean isNotSaved(Node node) {
        return !monitorResultRepository.existsByInternalId(getInternalId(node));
    }

    private String getInternalId(Node node) {
        return getContentElement(node).attr("data-lurker_list_id");
    }

    private String getHref(Node node) {
        return getContentElement(node).attr("href");
    }
}
