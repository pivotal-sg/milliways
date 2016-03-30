package io.pivotal.singapore.controllers;

import io.pivotal.singapore.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.composed.web.rest.json.GetJson;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TimeController {
    @Autowired
    private LocationService locationService;

    @GetJson("/")
    public Map<String, Object> index() {
        HashMap<String, Object> response = new HashMap<>();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        response.put("datetime", simpleDateFormat.format(calendar.getTime()));
        response.put("locations", locationService.getLocations());
        return response;
    }

}
