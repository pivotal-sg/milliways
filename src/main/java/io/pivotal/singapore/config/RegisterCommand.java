package io.pivotal.singapore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Configuration
public class RegisterCommand {
    @Value("${api.marvin.register}")
    private String marvinUrl;

    @PostConstruct
    void registerSubcommand() {
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> params = new HashMap<>();
        params.put("name", "time");
        params.put("endpoint", "http://localhost:8081/");
        params.put("method", "GET");
        restTemplate.postForObject(marvinUrl, params, HashMap.class);
    }


}
