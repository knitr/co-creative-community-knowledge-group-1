package com.cardiff.service.iface;

import com.cardiff.entity.Project;

import java.util.List;

public interface IProjectService {

    Project getProjectById(Long id);

    List<Project> findAllProjects();

    Project createProject(Project project);

    void updateProjectIdOnLocation(Project savedProject);
}
