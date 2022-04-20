package com.cardiff.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private List<Location> locations = null;
    private ProvidedLocation providedLocation;
    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public ProvidedLocation getProvidedLocation() {
        return providedLocation;
    }

    public void setProvidedLocation(ProvidedLocation providedLocation) {
        this.providedLocation = providedLocation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
