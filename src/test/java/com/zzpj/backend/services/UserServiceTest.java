package com.zzpj.backend.services;

import com.zzpj.backend.entities.User;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.services.implementations.UserService;

import com.zzpj.backend.utils.HashUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;


    @Test
    public void testGetUser() {
        UUID uuid = UUID.randomUUID();
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setUuid(uuid);

        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user1));

        User userFromService = userService.getUser(uuid).get();

        Mockito.verify(userRepository, Mockito.times(1)).findById(uuid);
        Assertions.assertEquals(user1.getLogin(), userFromService.getLogin());
    }

    @Test
    public void testGetAllUser() {
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setUuid(uuid1);
        User user2 = new User();
        user2.setPassword("456");
        user2.setLogin("test2");
        user2.setUuid(uuid2);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> usersFromService = userService.getAllUsers();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(users, usersFromService);
    }

    @Test
    public void testDeleteUser() {
        UUID uuid = UUID.randomUUID();
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setUuid(uuid);

        Mockito.doNothing().when(userRepository).deleteById(uuid);

        userService.deleteUser(uuid);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(uuid);
    }

}
