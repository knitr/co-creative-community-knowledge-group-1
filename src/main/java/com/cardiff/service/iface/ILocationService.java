package com.cardiff.service.iface;

import com.cardiff.domain.LatLng;
import com.cardiff.entity.Location;

import java.util.List;

public interface ILocationService {
    List<Location> findAllLocationsForMap();

    LatLng getCoordinatesForAddress(String address);

    Location save(Location location);
}
