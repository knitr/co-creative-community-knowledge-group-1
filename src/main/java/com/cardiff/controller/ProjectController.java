package com.cardiff.controller;

import com.cardiff.entity.Community;
import com.cardiff.entity.Project;
import com.cardiff.service.FragmentService;
import com.cardiff.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ProjectController {

    private ProjectService projectService;

    private FragmentService fragmentService;


    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }


    @Autowired
    public void setFragmentService(FragmentService fragmentService) {
        this.fragmentService = fragmentService;
    }

    @GetMapping("/project/{id}")
    public String showProjectDetails(WebRequest request, Model model, @PathVariable String id) {
        //model.addAttribute("user", new UserDto());
        Project projectById = projectService.getProjectById(Long.parseLong(id));

        if (projectById != null) {
            model.addAttribute("project", projectById);
        }
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "project";
    }

    @GetMapping("/projects")
    public String viewProjectList(WebRequest request, Model model) {


        model.addAttribute("projects", projectService.findAllProjects());
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "projectslist";
    }

    @GetMapping("/createProject/{id}")
    public String showCreateProjectPage(WebRequest request, Model model, @PathVariable Long id) {

        Project project = new Project();
        Community community = new Community();
        community.setId(id);
        project.setCommunity(community);
        model.addAttribute("project", project);
        model.addAttribute("communityList", fragmentService.getAllCommunitiesForNavigation());
        return "createProject";
    }

    @PostMapping("/newProject")
    public ModelAndView createNewProject(@ModelAttribute("project") @Valid Project project, RedirectAttributes redirAttrs) {
        ModelAndView mav = new ModelAndView("redirect:/project/");
        Project savedProject = null;
        try {
            savedProject = projectService.createProject(project);

            projectService.updateProjectIdOnLocation(savedProject);


            mav.setViewName(mav.getViewName() + savedProject.getId());
            mav.addObject("success", "Project successfully created");

        } catch (Exception ex) {
            mav.addObject("error", "Project name already exists");
            ex.printStackTrace();
            return mav;
        }
        return mav;

    }

}
