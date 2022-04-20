package com.cardiff.repository;
/*
    case study repository
    interface for JPA
    keep connecting with database table
 */
import com.cardiff.entity.CaseStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseStudyRepository extends JpaRepository<CaseStudy, Long> {
}
