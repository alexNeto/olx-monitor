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

    private static final String LOGTAG = "[TELEGRAM-BOT-HANDLER]";

    @Override
    public void onUpdateReceived(Update update) {
        log.info("{} New update Received", LOGTAG);
        try {
            Message message = update.getMessage();
            if (message != null && message.hasText()) {
                try {
                    onWaitingChannelMessage(message);
                } catch (InvalidObjectException e) {
                    log.error("{} A Error occurred during update", LOGTAG, e);
                }
            }
            log.info("{} Update completed", LOGTAG);
        } catch (Exception e) {
            log.error("{} A Error occurred during update", LOGTAG, e);
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
        log.info("{} Sending message", LOGTAG);
        try {
            execute(buildSendMessage(message));
            log.info("{} Message sent successfully", LOGTAG);
        } catch (TelegramApiException e) {
            log.error("{} Error sending the message", LOGTAG, e);
        }
    }

    private SendMessage buildSendMessage(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(message.getText());
        sendMessage.enableMarkdown(true);
        return sendMessage;
    }
}