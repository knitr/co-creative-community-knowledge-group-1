package com.cardiff.repository;
import com.cardiff.configuration.AuditorAwareImpl;
import com.cardiff.entity.CaseStudy;
import com.cardiff.entity.Community;
import com.cardiff.entity.User;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
@Ignore
class CaseStudyRepositoryTest {



    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CaseStudyRepository repo;

    @BeforeEach
    void setUp() {

        // add principal object to SecurityContextHolder
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("tester");
        user.setId(100L);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(auth);


    }

    //@Test
    /*
        test the connection
     */
    @Ignore
    public void testCaseStudy() {
        CaseStudy caseStudy = new CaseStudy();//create test object
        caseStudy.setTitle("Tir Coed");
        caseStudy.setCaseIntroduction("Tir Coed");
        CaseStudy savedCaseStudy = repo.save(caseStudy);//save test object into database
        CaseStudy existingCaseStudy = entityManager.find(CaseStudy.class, savedCaseStudy.getId());//find the object just created
        assertThat(existingCaseStudy.getTitle()).isEqualTo(savedCaseStudy.getTitle());//check these two object same or not, if same, return pass
    }
    /*
        check if the object have already saved in database
     */
    //@Test
    @Ignore
    public void testFindAll() {
        List<CaseStudy> all = repo.findAll();
        assertThat(all.size()).isEqualTo(1);//if same, return pass
    }
    /*
        check the attribute that just created has saved in database properly
     */

    //@Test
    @Ignore
    public void getCommunityByIdTest() {
        CaseStudy caseStudy = entityManager.find(CaseStudy.class, 3L);
        assertThat(caseStudy.getId()).isEqualTo(3L);
    }


}
