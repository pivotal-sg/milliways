package io.pivotal.singapore.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Getter
    private Map<String, String> locations;

    LocationService() {
        locations = initLocations();
    }

    private Map<String, String> initLocations() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        return availableZoneIds
                .parallelStream()
                .map(this::extractCity)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, this::mergeFunction));
    }

    private String mergeFunction(String oldValue, String newValue) {
        return oldValue; //just keep the value associated with the first found key
    }

    private Entry<String, String> extractCity(String zone) {
        String[] fragments = zone.split("/");
        String location = fragments[fragments.length - 1].replace("_", " ").toLowerCase();

        return new AbstractMap.SimpleImmutableEntry<>(location, zone);
    }

    public String getTimeForLocation(String location) {
        String zone = locations.get(location);
        ZoneId zoneId = ZoneId.of(zone);
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        return zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_TIME);
    }
}
