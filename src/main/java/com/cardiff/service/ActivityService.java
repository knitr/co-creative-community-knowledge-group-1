package com.cardiff.service;

import com.cardiff.entity.Activity;
import com.cardiff.repository.ActivityRepository;
import com.cardiff.repository.CommunityRepository;
import com.cardiff.service.iface.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements IActivityService {

    private ActivityRepository activityRepository;

    @Autowired
    public void setCommunityRepository(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }
}
