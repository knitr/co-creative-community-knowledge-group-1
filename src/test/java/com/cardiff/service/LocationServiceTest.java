package com.cardiff.service;

import com.cardiff.domain.LatLng;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class LocationServiceTest {

    @Autowired
    private LocationService locationService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getCoordinatesForAddress_Success() {

        LatLng coordinatesForAddress = locationService.getCoordinatesForAddress("CF24 4PH");

        assertNotNull(coordinatesForAddress);
        assertNotNull(coordinatesForAddress.getLat());
        assertNotNull(coordinatesForAddress.getLng());
        assertNotNull(coordinatesForAddress.getMapUrl());


    }


    @Test
    void getCoordinatesForAddress_Failure() {

        LatLng coordinatesForAddress = locationService.getCoordinatesForAddress("");

        assertNull(coordinatesForAddress);


    }
}