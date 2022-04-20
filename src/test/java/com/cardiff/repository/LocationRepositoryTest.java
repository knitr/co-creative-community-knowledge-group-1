package com.cardiff.repository;

import com.cardiff.configuration.AuditorAwareImpl;
import com.cardiff.entity.Community;
import com.cardiff.entity.Location;
import com.cardiff.entity.User;
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

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(true)
class LocationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocationRepository repo;


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

    @Test
    public void testCreateLocation_Success() {
        Location location = new Location();
        location.setName("Tir Coed");
        location.setLatitude(BigDecimal.valueOf(31.764));
        location.setLatitude(BigDecimal.valueOf(31.764));
        Location savedLocation = repo.save(location);
        Location existingLocation = entityManager.find(Location.class, savedLocation.getId());
        assertThat(existingLocation.getName()).isEqualTo(savedLocation.getName());
        repo.delete(existingLocation);
    }


}