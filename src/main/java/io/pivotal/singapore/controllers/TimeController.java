package io.pivotal.singapore.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.composed.web.rest.json.GetJson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TimeController {
    @GetJson("/")
    public Map<String, String> index() {
        HashMap<String, String> response = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        response.put("datetime", simpleDateFormat.format(calendar.getTime()));
        return response;
    }
}
