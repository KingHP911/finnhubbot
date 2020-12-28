package ru.kinghp.finnhubbot;

import com.github.oscerd.finnhub.client.FinnhubClient;
import com.github.oscerd.finnhub.model.CompanyProfile;
import com.github.oscerd.finnhub.model.Quote;
import lombok.Data;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

@Data
public class FinnHubData {

    private FinnhubClient client;

    public FinnHubData() {
    }

    public FinnHubData(FinnhubClient client) {
        this.client = client;
    }


    public Quote getQuote(String ticker){

        Quote quote = null;
        try {
            quote = client.getQuote(ticker);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quote;
    }

    public CompanyProfile getCompanyProfile(String ticker){

        CompanyProfile companyProfile = null;
        try {
            companyProfile = client.getCompanyProfile(ticker);
            if(companyProfile.getName() == null){
                return null;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return companyProfile;
    }
}
