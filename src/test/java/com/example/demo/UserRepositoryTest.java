package com.example.demo;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void clearDatabase() {
        userRepository.deleteAll();
    }

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setEmail("uniqueEmail@example.com"); // Ensure a unique email
        user.setPassword("password123");

        userRepository.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void findByIdTest() {
        User user = new User();
        user.setEmail("findById@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        assertNotNull(user.getId());
        Optional<User> foundUser = userRepository.findById(user.getId());
        assertTrue(foundUser.isPresent());
    }

    @Test
    public void updateUserTest() {
        User user = new User();
        user.setEmail("updateUser@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        assertNotNull(user.getId());

        user.setPassword("newPassword123");
        userRepository.save(user);

        Optional<User> updatedUser = userRepository.findById(user.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("newPassword123", updatedUser.get().getPassword());
    }

    @Test
    public void deleteUserTest() {
        User user = new User();
        user.setEmail("deleteUser@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        assertNotNull(user.getId());

        userRepository.delete(user);

        Optional<User> deletedUser = userRepository.findById(user.getId());
        assertFalse(deletedUser.isPresent());
    }
}
