package io.pivotal.singapore.controllers;

import io.pivotal.singapore.services.LocationService;
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
        String timeForLocation = locationService.getTimeForLocation(location.toLowerCase());

        response.put("message", String.format("The time in %s is %s", location, timeForLocation));
        response.put("message_type", "channel");
        return response;
    }
}
