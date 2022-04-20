package com.cardiff.repository;

import com.cardiff.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
}
