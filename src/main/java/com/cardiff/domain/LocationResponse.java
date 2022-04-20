package com.cardiff.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationResponse {

    private final Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private List<Result> results = null;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
