package airtable;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

public class AirtableApiService {

    @Resource
    public RestTemplate restTemplate;

    private static final String BASEURL = "https://api.airtable.com/v0/appNfLDqqsUaEz7PU/";

    private String apiKey;

    public AirtableApiService(String apikey) {
        this.apiKey = apikey;
    }

    public String makeApiRequest(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        return restTemplate.getForObject(queryFor(query), String.class, entity);
    }

    private String queryFor(String query) {
        return BASEURL + query + "?api_key=" + apiKey;
    }
}
