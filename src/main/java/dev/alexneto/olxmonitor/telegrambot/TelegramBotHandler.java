package dev.alexneto.olxmonitor.telegrambot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InvalidObjectException;

@Service
@Slf4j
public class TelegramBotHandler extends TelegramLongPollingBot {

    @Value("${telegram-bot.username}")
    private String botUsername;
    @Value("${telegram-bot.token}")
    private String botToken;

    private static final String LOGTAG = "[CHANNELHANDLERS]";

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                try {
                    onWaitingChannelMessage(message);
                } catch (InvalidObjectException e) {
                    log.error(LOGTAG, e);
                }
            }
        } catch (Exception e) {
            log.error(LOGTAG, e);
        }
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    private void onWaitingChannelMessage(Message message) throws InvalidObjectException {
        sendMessage(message);
    }

    public void sendMessage(Message message) {
        log.info("Sending message");
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());

        sendMessage.setText(message.getText());
        sendMessage.enableMarkdown(true);

        try {
            execute(sendMessage);
            log.info("Message sent successfully");
        } catch (TelegramApiException e) {
            log.error("Error sending the message", e);
        }
    }
}