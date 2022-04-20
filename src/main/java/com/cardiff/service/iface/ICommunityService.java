package com.cardiff.service.iface;

import com.cardiff.entity.Community;


public interface ICommunityService {
        Community createCommunity(Community community) throws Exception;
        Community getCommunityById(Long id);

}
