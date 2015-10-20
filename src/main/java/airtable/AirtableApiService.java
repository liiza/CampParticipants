package airtable;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

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

    public String makeGETApiRequest(String query) {
        HttpEntity<String> entity = new HttpEntity<String>("parameters", signRequest(new HttpHeaders()));
        return restTemplate.getForObject(queryFor(query), String.class, entity);
    }

    public String makePOSTApiRequest(String query, String jsonInput) throws AirtableApiError {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity request= new HttpEntity(jsonInput, signRequest(httpHeaders));

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(queryFor(query), request, String.class);
        if (!responseEntity.getStatusCode().is2xxSuccessful()){
            throw new AirtableApiError();
        }
        return responseEntity.getBody();

    }

    private HttpHeaders signRequest(HttpHeaders headers) {
        headers.set("Authorization", "Bearer " + apiKey);
        return headers;

    }


    
    private String queryFor(String query) {
        return BASEURL + appId + "/" +  query + "?api_key=" + apiKey;
    }
}
