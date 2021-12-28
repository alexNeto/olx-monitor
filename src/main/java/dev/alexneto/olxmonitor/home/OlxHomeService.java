package dev.alexneto.olxmonitor.home;

import dev.alexneto.olxmonitor.home.model.HomeResult;
import dev.alexneto.olxmonitor.home.model.dto.HomeResultDTO;
import dev.alexneto.olxmonitor.home.scrappy.HomeDetailCollectorService;
import dev.alexneto.olxmonitor.home.scrappy.NewHomeMonitorService;
import dev.alexneto.olxmonitor.olx.MonitorType;
import dev.alexneto.olxmonitor.olx.OlxMonitorRepository;
import dev.alexneto.olxmonitor.olx.model.OlxMonitor;
import dev.alexneto.olxmonitor.telegrambot.TelegramBotHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OlxHomeService {

    private final OlxMonitorRepository olxMonitorRepository;
    private final HomeResultRepository monitorResultRepository;
    private final TelegramBotHandler botHandler;
    private final NewHomeMonitorService newHomeMonitorService;
    private final HomeDetailCollectorService homeDetailCollectorService;

    private static final String LOGTAG = "[OLX-HOME-MONITOR-SERVICE] ";

    @Scheduled(fixedDelay = 600000)
    public void verifyNewItems() {
        log.info("{} Started new verification", LOGTAG);
        List<OlxMonitor> olxModelList = olxMonitorRepository.findAll();
        olxModelList.stream()
                .filter(i -> MonitorType.HOME.equals(i.getMonitorType()))
                .forEach(this::verifyNewItem);
        log.info("{} Verification completed", LOGTAG);
    }

    private void verifyNewItem(OlxMonitor olxMonitor) {
        List<String> urls = newHomeMonitorService.verifyNewItems(olxMonitor.getUrlToMonitor());
        urls.stream()
                .map(homeDetailCollectorService::getData)
                .forEach(homeResult -> {
                    try {
                        homeResult.setOlxMonitorId(olxMonitor.getId());
                        botHandler.sendMessage(createNewMessage(olxMonitor.getChatId(), buildMessage(homeResult)));
                        monitorResultRepository.save(homeResult);
                        log.info("{} New result saved {}", LOGTAG, homeResult.getInternalId());
                    } catch (Exception e) {
                        log.error("{} A Error occurred during the update", LOGTAG, e);
                    }
                });
    }

    private String buildMessage(HomeResult homeResult) {
        String messageTemplate = "**[#title](#url)**\n\n" +
                "Valor do Aluguel: #price\n" +
                "Quartos: #rooms\n" +
                "Espaços na Garagem: #garageSpace\n" +
                "\n" +
                "#neighborhood\n" +
                "#streetName";

        return messageTemplate
                .replace("#title", homeResult.getTitle())
                .replace("#url", homeResult.getUrl())
                .replace("#price", homeResult.getPrice())
                .replace("#rooms", homeResult.getRooms())
                .replace("#garageSpace", homeResult.getGarageSize())
                .replace("#neighborhood", homeResult.getNeighborhood())
                .replace("#streetName", homeResult.getStreetName());
    }

    private Message createNewMessage(long chatId, String text) {
        Message message = new Message();
        Chat chat = new Chat();
        chat.setId(chatId);
        message.setChat(chat);
        message.setText(text);
        return message;
    }

    @Cacheable(value = "all-home-results-from-olx-model")
    public List<HomeResultDTO> findAllHomeResultByOlxModelId(long id) {
        return monitorResultRepository.getHomeResultByOlxMonitorId(id)
                .stream()
                .map(HomeResultConverter::toDTO)
                .collect(Collectors.toList());
    }
}
