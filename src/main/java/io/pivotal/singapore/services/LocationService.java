package io.pivotal.singapore.services;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Getter
    private Set<String> locations;

    LocationService() {
        locations = initLocations();
    }

    private Set<String> initLocations() {
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();

        return availableZoneIds
                .parallelStream()
                .map(this::extractCity)
                .collect(Collectors.toSet());
    }

    private String extractCity(String zone) {
        String[] fragments = zone.split("/");
        return fragments[fragments.length - 1].replace("_", " ").toLowerCase();
    }
}
