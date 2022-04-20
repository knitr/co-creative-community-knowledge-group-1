
package com.cardiff.domain;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

public class ProvidedLocation {

    private String location;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
