package com.cardiff.service;

import com.cardiff.entity.Resource;
import com.cardiff.repository.ResourcesRepository;
import com.cardiff.service.iface.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService implements IResourceService {

    private ResourcesRepository resourcesRepository;

    @Autowired
    public void setResourcesRepository(ResourcesRepository resourcesRepository) {
        this.resourcesRepository = resourcesRepository;
    }

    @Override
    public Resource createResource(Resource resource) {
        return resourcesRepository.save(resource);
    }
}
