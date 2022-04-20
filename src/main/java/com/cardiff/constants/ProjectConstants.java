package com.cardiff.constants;

public enum ProjectConstants {

    OPEN_MAP_REQUEST_URL("https://open.mapquestapi.com/geocoding/v1/address"),
    API_KEY("GeVVrZ56KA0LHyOdR7jj9t8YAoeWstCd");

    public final String value;

    ProjectConstants(String value) {
        this.value = value;
    }

}
