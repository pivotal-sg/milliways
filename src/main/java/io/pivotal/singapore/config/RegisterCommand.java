package io.pivotal.singapore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class RegisterCommand {
    @Value("${api.marvin.register}")
    private String marvinUrl;

    @PostConstruct
    void registerSubcommand() {
        HashMap<String, String> location = new HashMap<>();
        location.put("location", "/^\\b(\\w+)/");

        List<Map<String, String>> arguments = new ArrayList<>();
        arguments.add(location);

        HashMap<String, Object> subCommand = new HashMap<>();
        subCommand.put("name", "in");
        subCommand.put("endpoint", "http://localhost:8081/");
        subCommand.put("method", "GET");
        subCommand.put("arguments", arguments);

        List<Map<String, Object>> subCommands = new ArrayList<>();
        subCommands.add(subCommand);

        HashMap<String, Object> command = new HashMap<>();
        command.put("name", "time");
        command.put("endpoint", "http://localhost:8081/");
        command.put("method", "GET");
        command.put("subCommands", subCommands);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(marvinUrl, command, HashMap.class);
    }
}
