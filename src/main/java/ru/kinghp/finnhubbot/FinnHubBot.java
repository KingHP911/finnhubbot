package ru.kinghp.finnhubbot;

import com.github.oscerd.finnhub.model.CompanyProfile;
import com.github.oscerd.finnhub.model.Quote;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Data
public class FinnHubBot extends TelegramWebhookBot {

    private String webHookPath;
    private String botUserName;
    private String botToken;
    @Autowired
    private FinnHubData hubData;

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    //обрабатываем полученные update
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.getMessage() != null && update.getMessage().hasText()) {
            Long chat_id = update.getMessage().getChatId();
            try {
                String ticker = update.getMessage().getText();
                if (!ticker.isEmpty()){
                    CompanyProfile profile = hubData.getCompanyProfile(ticker);
                    Quote quote = hubData.getQuote(ticker);
                    String message = "";

                    if (profile != null){
                        message = "Company name: " + profile.getName() + "\n" +
                                "Current price: " + quote.getCurrentPrice();

                    }else {
                        message = "Company not found";

                    }
                    execute(new SendMessage(chat_id.toString(), message));
                }


            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
