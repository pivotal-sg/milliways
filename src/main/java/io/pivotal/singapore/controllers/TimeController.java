package io.pivotal.singapore.controllers;

import io.pivotal.singapore.services.LocationService;
import io.pivotal.singapore.services.exceptions.UnknownLocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.composed.web.rest.json.GetJson;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TimeController {
    @Autowired
    private LocationService locationService;

    @GetJson("/")
    public Map<String, Object> index(@RequestParam String location) throws Exception {
        if (location == null) {
            throw new Exception("No location provided");
        }

        HashMap<String, Object> response = new HashMap<>();

        String responseMessage;

        try {
            String timeForLocation = locationService.getTimeForLocation(location.toLowerCase());
            responseMessage = String.format("The time in %s is %s", location, timeForLocation);
        } catch (UnknownLocationException ex) {
            responseMessage = String.format("%s is not a recognized city", location);
        }
        response.put("message", responseMessage);

        response.put("message_type", "channel");
        return response;
    }
}
