package com.cardiff.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "communities")
public class Community extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, length = 1000)
    private String aboutUs;

    @Column(nullable = false, length = 1000)
    private String goal;

    @Column(nullable = false, length = 1000)
    private String history;

    @Column(nullable = true, length = 1000)
    private String photo;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Activity> activities;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Project> projects;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<CaseStudy> caseStudies;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Resource> resources;


    public Community(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Community() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Transient
    public String getPhotosImagePath() {
        if (photo == null || id == null) return null;
        return "/src/main/resources/static/images/" + id + "/" + this.photo;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Set<CaseStudy> getCaseStudies() {
        return caseStudies;
    }

    public void setCaseStudies(Set<CaseStudy> caseStudies) {
        this.caseStudies = caseStudies;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
