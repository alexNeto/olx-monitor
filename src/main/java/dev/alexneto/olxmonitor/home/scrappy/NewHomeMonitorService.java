package dev.alexneto.olxmonitor.home.scrappy;

import dev.alexneto.olxmonitor.home.HomeResultRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewHomeMonitorService {

    private final HomeResultRepository monitorResultRepository;

    public List<String> verifyNewItems(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        Element adListContainer = document.getElementById("ad-list");
        if (adListContainer != null) {
            return filterNotSaved(adListContainer.childNodes());
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

    private boolean isNotSaved(Node node) {
        return !monitorResultRepository.existsByInternalId(getInternalId(node));
    }

    private String getInternalId(Node node) {
        return getContentElement(node).attr("data-lurker_list_id");
    }

    private String getHref(Node node) {
        return getContentElement(node).attr("href");
    }
}
