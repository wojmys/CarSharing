package com.example.carsharing.repository;

import com.example.carsharing.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp(){
        user=User.builder()
                .name("Joseph Smith")
                .isTopCustomer(true)
                .build();
        userRepository.save(user);
    }

    @Test
    void testCreateUser(){
        //then
        assertNotNull(user);
        //clean
        userRepository.deleteById(user.getId());
    }

    @Test
    void deleteUserById(){
        // when
        boolean existsBeforeDelete = userRepository.findById(user.getId()).isPresent();
        userRepository.deleteById(user.getId());
        boolean existsAfterDelete = userRepository.findById(user.getId()).isPresent();
        //then
        assertTrue(existsBeforeDelete);
        assertFalse(existsAfterDelete);
    }
}
