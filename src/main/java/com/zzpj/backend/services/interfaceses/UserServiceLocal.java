package com.zzpj.backend.services.interfaceses;



import com.zzpj.backend.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceLocal {

    void addUser(User user);
    Optional<User> getUser(UUID id);
    List<User> getAllUsers();
    void deleteUser(UUID id);
    void editUser(User user);
}
