package com.cardiff.service;

import com.cardiff.entity.Community;
import com.cardiff.repository.CommunityRepository;
import com.cardiff.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FragmentService {

    private CommunityRepository communityRepository;

    @Autowired
    public void setCommunityRepository(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> getAllCommunitiesForNavigation() {
        return communityRepository.findAll();

    }

}
