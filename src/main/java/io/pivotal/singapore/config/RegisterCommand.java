package io.pivotal.singapore.config;

import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.*;

@Configuration
public class RegisterCommand {
    @Value("${api.marvin.register}")
    private String marvinUrl;

    @Value("${base.url}")
    private String baseUrl;

    final static private Logger logger = getLogger(RegisterCommand.class);

    @PostConstruct
    void registerSubcommand() {
        RestTemplate restTemplate1 = new RestTemplate();
        logger.error(">>>>>>>>>>> GETTING COMMANDS REGISTERED WITH MARVIN <<<<<<<<<<<<<<<");
        String response = restTemplate1.getForObject(marvinUrl, String.class);
        logger.error(response);
        logger.error(">>>>>>>>>>> GOT COMMANDS REGISTERED WITH MARVIN <<<<<<<<<<<<<<<");


        HashMap<String, String> location = new HashMap<>();
        location.put("location", "/^\\b(\\w+)/");

        List<Map<String, String>> arguments = new ArrayList<>();
        arguments.add(location);

        HashMap<String, Object> subCommand = new HashMap<>();
        subCommand.put("name", "in");
        subCommand.put("endpoint", baseUrl);
        subCommand.put("method", "GET");
        subCommand.put("arguments", arguments);

        List<Map<String, Object>> subCommands = new ArrayList<>();
        subCommands.add(subCommand);

        HashMap<String, Object> command = new HashMap<>();
        command.put("name", "time");
        command.put("endpoint", baseUrl);
        command.put("method", "GET");
        command.put("subCommands", subCommands);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(marvinUrl, command, HashMap.class);
    }
}
