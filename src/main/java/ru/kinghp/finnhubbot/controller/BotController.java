package ru.kinghp.finnhubbot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kinghp.finnhubbot.FinnHubBot;

@RestController
public class BotController {

    private FinnHubBot finnHubBot;

    public BotController(FinnHubBot finnHubBot) {
        //autowared из FinnHubBotConfig
        this.finnHubBot = finnHubBot;
    }

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return finnHubBot.onWebhookUpdateReceived(update);
    }


}
