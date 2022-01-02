package dev.alexneto.olxmonitor.car;

import dev.alexneto.olxmonitor.car.model.CarResult;
import dev.alexneto.olxmonitor.car.model.dto.CarResultDTO;
import dev.alexneto.olxmonitor.car.scrappy.CarDetailCollectorService;
import dev.alexneto.olxmonitor.car.scrappy.CarMonitorService;
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
public class OlxCarService {

    private final OlxMonitorRepository olxMonitorRepository;
    private final CarResultRepository monitorResultRepository;
    private final TelegramBotHandler botHandler;
    private final CarMonitorService carMonitorService;
    private final CarDetailCollectorService carDetailCollectorService;

    private static final String LOGTAG = "[OLX-CAR-MONITOR-SERVICE] ";

    @Scheduled(fixedDelay = 600000)
    public void verifyNewItems() {
        log.info("{} Started new verification", LOGTAG);
        List<OlxMonitor> olxModelList = olxMonitorRepository.findAll();
        olxModelList.stream()
                .filter(i -> MonitorType.CAR.equals(i.getMonitorType()))
                .forEach(this::verifyNewItem);
        log.info("{} Verification completed", LOGTAG);
    }

    private void verifyNewItem(OlxMonitor olxMonitor) {
        carMonitorService.verifyNewItems(olxMonitor.getUrlToMonitor())
                .stream()
                .map(carDetailCollectorService::getData)
                .forEach(carResult -> {
                    try {
                        carResult.setOlxMonitorId(olxMonitor.getId());
                        botHandler.sendMessage(createNewMessage(olxMonitor.getChatId(), buildMessage(carResult)));
                        monitorResultRepository.save(carResult);
                        log.info("{} New result saved {}", LOGTAG, carResult.getInternalId());
                    } catch (Exception e) {
                        log.error("{} A Error occurred during the update", LOGTAG, e);
                    }
                });
    }

    private String buildMessage(CarResult carResult) {
        String messageTemplate = "**[#title](#url)**\n\n" +
                "Valor do Carro: #price\n" +
                "Modelo: #model\n" +
                "Marca: #brand\n" +
                "Tipo: #carType\n" +
                "Ano: #year\n" +
                "Kilometragem: #mileage\n" +
                "Potência: #motorPower\n" +
                "\n" +
                "#city\n" +
                "#neighborhood";
        return messageTemplate
                .replace("#title", carResult.getTitle())
                .replace("#url", carResult.getUrl())
                .replace("#price", carResult.getPrice())
                .replace("#model", carResult.getModel())
                .replace("#brand", carResult.getBrand())
                .replace("#carType", carResult.getCarType())
                .replace("#year", carResult.getYear())
                .replace("#mileage", carResult.getMileage())
                .replace("#motorPower", carResult.getMotorPower())
                .replace("#city", carResult.getCity())
                .replace("#neighborhood", carResult.getNeighborhood());
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
    public List<CarResultDTO> findAllCarResultByOlxModelId(long id) {
        return monitorResultRepository.getCarResultByOlxMonitorId(id)
                .stream()
                .map(CarResultConverter::toDTO)
                .collect(Collectors.toList());
    }
}
