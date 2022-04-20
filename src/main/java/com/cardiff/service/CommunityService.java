package com.cardiff.service;

import com.cardiff.entity.Community;
import com.cardiff.repository.CommunityRepository;
import com.cardiff.service.iface.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityService implements ICommunityService {

    private static CommunityRepository communityRepository;

    @Autowired
    public void setCommunityRepository(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public Community createCommunity(Community community) throws Exception {

        return communityRepository.save(community);
    }

    @Override
    public Community getCommunityById(Long id) {
        return communityRepository.getById(id);
    }
}

