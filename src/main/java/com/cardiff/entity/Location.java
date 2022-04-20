package com.cardiff.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "locations")
public class Location extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 45)
    private String name;

    @Column(precision = 9, scale = 3)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 3)
    private BigDecimal longitude;

    @Column
    private String mapUrl;

    @Column
    private Long projectId;

    //made it transient because this only to propagate project description to the popup on locations page
    @Transient
    private String projectDescription;

    public Location(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Transient
    public String getProjectDescription() {
        return projectDescription;
    }

    @Transient
    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}

