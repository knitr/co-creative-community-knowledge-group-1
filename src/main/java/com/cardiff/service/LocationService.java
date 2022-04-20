package com.cardiff.service;

import com.cardiff.constants.ProjectConstants;
import com.cardiff.domain.LatLng;
import com.cardiff.domain.LocationResponse;
import com.cardiff.entity.Location;
import com.cardiff.entity.Project;
import com.cardiff.repository.LocationRepository;
import com.cardiff.service.iface.ILocationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class LocationService implements ILocationService {
    private LocationRepository locationRepository;

    private ProjectService projectService;

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAllLocationsForMap() {
        return populateProjectDescriptionForMap(locationRepository.findAll());
    }


    /**
     * This methods creates a http request to open-maprequest-api to get the coordinates for the given address
     *
     * @param address
     * @return
     */
    @Override
    public LatLng getCoordinatesForAddress(String address) {
        LatLng latLng = null;
        try {
            if (address != null)
                address.replace(" ", "%20");

            String url = ProjectConstants.OPEN_MAP_REQUEST_URL.value + "?key=" + ProjectConstants.API_KEY.value + "&location=" + address;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity<LocationResponse> request = new HttpEntity<>(httpHeaders);

            ResponseEntity<LocationResponse> response = restTemplate
                    .exchange(url, HttpMethod.GET, request, LocationResponse.class);
            LocationResponse body = response.getBody();

            Gson gson = new Gson();
            //logging response for the http request
            System.out.println(gson.toJson(response));
            AtomicReference<String> mapUri = new AtomicReference<>();

            //from the location we are selecting the first latitude and longitude
            Optional<LatLng> location = body.getResults().stream().map(result -> {
                mapUri.set(result.getLocations().get(0).getMapUrl());
                return result.getLocations().get(0).getLatLng();
            }).findAny();


            //The third party doesn't send mapri in the same object as the Latitude and longitude
            // adding into a dummy field mapuri to propagate this value to save in the entity
            if (location.isPresent()) {
                location.get().setMapUrl(mapUri.get());
                latLng = location.get();
            }


        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return latLng;
    }


    private List<Location> populateProjectDescriptionForMap(List<Location> locations) {

        locations.stream().forEach(location -> {
            if (location.getProjectId() != null) {
                Project projectById = projectService.getProjectById(location.getProjectId());
                location.setProjectDescription(projectById.getDescription());
            }
        });
        System.out.println(new Gson().toJson(locations));
        return locations;
    }

    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
