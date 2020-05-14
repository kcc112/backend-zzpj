package com.zzpj.backend.services;



import com.zzpj.backend.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceLocal {

    void addUser(User user);
    Optional<User> getUser(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    void editUser(User user);
}
