package airtable;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConf {

    @Bean
    public RestTemplate restTemplate(){
      return new RestTemplate();
    }

    @Bean
    public AirtableApiService apiService(){
        String apiKey = System.getenv("api_key");
        return new AirtableApiService(apiKey);
    }
}
