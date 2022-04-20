package com.cardiff.service;

import com.cardiff.domain.LatLng;
import com.cardiff.entity.Community;
import com.cardiff.entity.Location;
import com.cardiff.entity.Project;
import com.cardiff.repository.ProjectRepository;
import com.cardiff.service.iface.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProjectService implements IProjectService {

    private ProjectRepository projectRepository;

    private LocationService locationService;

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.getById(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }


    /**
     * This method creates project and populates location of the project
     *
     * @param project
     * @return
     */
    @Override
    public Project createProject(Project project) {
        Community community = new Community();
        community.setId(Long.valueOf(project.getCommunityId()));
        project.setCommunity(community);


        //get Coordinates from open map-request api for the given address
        LatLng coordinatesForAddress = locationService.getCoordinatesForAddress(project.getAddress());
        if (coordinatesForAddress != null) {
            Location location = new Location();
            location.setLatitude(BigDecimal.valueOf(coordinatesForAddress.getLat()));
            location.setLongitude(BigDecimal.valueOf(coordinatesForAddress.getLng()));
            location.setName(project.getName());
            location.setMapUrl(coordinatesForAddress.getMapUrl());
            project.setLocation(location);
        }
        return projectRepository.save(project);
    }


    /**
     * This method populates the project id on the location object this will enable user from locations map to navigate to the project page
     *
     * @param savedProject
     */
    @Override
    public void updateProjectIdOnLocation(Project savedProject) {
        Location location = savedProject.getLocation();
        location.setProjectId(savedProject.getId());
        locationService.save(location);
    }


}
