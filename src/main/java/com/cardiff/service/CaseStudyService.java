package com.cardiff.service;

import com.cardiff.entity.CaseStudy;
import com.cardiff.repository.CaseStudyRepository;
import org.springframework.stereotype.Service;

/*
    service layer of SpringMVC
 */

@Service
public class CaseStudyService {
    private CaseStudyRepository caseStudyRepository;

    public CaseStudyService(CaseStudyRepository caseStudyRepository) {
        this.caseStudyRepository = caseStudyRepository;
    }
     //find case study id
    public CaseStudy findById(Long id){
        return caseStudyRepository.findById(id).get();
    }
    //save case study
    public CaseStudy saveCaseStudy(CaseStudy casestudy)  {
        return caseStudyRepository.save(casestudy);
    }


}
