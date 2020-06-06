package com.zzpj.backend.services;

import com.zzpj.backend.entities.User;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.services.implementations.UserService;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testAddUser() {
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setType("CLIENT");
        user1.setId(1L);

        Mockito.when(userRepository.save(user1)).thenReturn(user1);

        userService.addUser(user1);

        Mockito.verify(userRepository, Mockito.times(1)).save(user1);
    }

    @Test
    void testGetUser() {
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setType("CLIENT");
        user1.setId(1L);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        User userFromService = userService.getUser(1L).get();

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Assertions.assertEquals(user1.getLogin(), userFromService.getLogin());
    }

    @Test
    void testGetAllUser() {
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setType("CLIENT");
        user1.setId(1L);
        User user2 = new User();
        user2.setPassword("456");
        user2.setLogin("test2");
        user2.setType("CLIENT");
        user2.setId(2L);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(users);

        List<User> usersFromService = userService.getAllUsers();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Assertions.assertEquals(users, usersFromService);
    }

    @Test
    void testDeleteUser() {
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setType("CLIENT");
        user1.setId(1L);

        Mockito.doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void testEditUser() {
        User user1 = new User();
        user1.setPassword("123");
        user1.setLogin("test1");
        user1.setType("CLIENT");
        user1.setId(1L);
        User user2 = new User();
        user2.setPassword("456");
        user2.setLogin("test2");
        user2.setType("CLIENT");
        user2.setId(1L);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Mockito.when(userRepository.save(user1)).thenReturn(user1);

        userService.editUser(user2);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).save(user1);
        Assertions.assertEquals(user2.getLogin(), user1.getLogin());
        Assertions.assertEquals(user2.getId(), user1.getId());
        Assertions.assertEquals(user2.getType(), user1.getType());
        Assertions.assertEquals(DigestUtils.sha256Hex(user2.getPassword()), user1.getPassword());
    }
}
