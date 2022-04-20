package com.cardiff.entity;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(nullable = false, length = 45)
    private String author;

    @Column(length = 1000)
    private String description;

    @Column(length = 1000)
    private String objective;

    @Column(length = 1000)
    private String about;

    @Column
    private String url;

    @Transient
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "community_id", nullable = false, referencedColumnName = "id")
    private Community community;

    //made this transient to propagate community ID between the form and controller
    @Transient
    private int communityId;

    @Transient
    public int getCommunityId() {
        return communityId;
    }

    @Transient
    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    @Transient
    public String getAddress() {
        return address;
    }

    @Transient
    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
