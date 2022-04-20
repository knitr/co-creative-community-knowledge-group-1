package com.cardiff.controller;

import com.cardiff.service.FragmentService;
import com.cardiff.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
public class LocationController {


    private FragmentService fragmentService;
    private LocationService locationService;

    @Autowired
    public void setCommunityService(FragmentService fragmentService) {
        this.fragmentService = fragmentService;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * This methods populated locations in the model and navigates to the locations page
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/locations")
    public String showLocationsPage(WebRequest request, Model model) {

        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        //find all locations from db to be displayed on the map
        model.addAttribute("locations", locationService.findAllLocationsForMap());
        return "projectmap";
    }


}
