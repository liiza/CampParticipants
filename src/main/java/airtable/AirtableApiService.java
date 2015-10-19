package airtable;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

public class AirtableApiService {


    @Resource
    public RestTemplate restTemplate;

    private static final String BASEURL = "https://api.airtable.com/v0/";

    private final String apiKey;
    private final String appId;

    public AirtableApiService(String apikey, String appId) {
        this.apiKey = apikey;
        this.appId = appId;
    }

    public String makeApiRequest(String query) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        return restTemplate.getForObject(queryFor(query), String.class, entity);
    }

    private String queryFor(String query) {
        return BASEURL + appId + "/" +  query + "?api_key=" + apiKey;
    }
}
