package dev.alexneto.olxmonitor.telegrambot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TelegramBotConfig {

    private final TelegramBotHandler botHandler;

    private static final String LOGTAG = "[TELEGRAM-BOT-CONFIG]";

    @Bean
    public void setupTelegramBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(botHandler);
            log.debug("{} Bot configured successfully", LOGTAG);
        } catch (TelegramApiException e) {
            log.error("{} Error configuring the bot", LOGTAG, e);
        }
    }
}
