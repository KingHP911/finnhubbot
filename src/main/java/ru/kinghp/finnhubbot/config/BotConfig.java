package ru.kinghp.finnhubbot.config;

import com.github.oscerd.finnhub.client.FinnhubClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kinghp.finnhubbot.FinnHubBot;
import ru.kinghp.finnhubbot.FinnHubData;

@Data
@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {

    private String botUserName;
    private String webHookPath;
    private String botToken;
    private String fhtoken;

    @Bean
    public FinnHubBot finnHubBot(){

        FinnHubBot finnHubBot = new FinnHubBot();
        finnHubBot.setBotUserName(botUserName);
        finnHubBot.setBotToken(botToken);
        finnHubBot.setWebHookPath(webHookPath);

        return finnHubBot;
    }

    @Bean
    public FinnhubClient finnhubClient(){
        return new FinnhubClient(fhtoken);
    }

    @Bean
    public FinnHubData finnHubData(FinnhubClient finnhubClient){
        FinnHubData hubData = new FinnHubData(finnhubClient);
        return hubData;
    }

}
