package com.zzpj.backend.services.interfaceses;



import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserServiceLocal {

    Optional<User> getUser(UUID id);
    List<User> getAllUsers();
    void deleteUser(UUID id);
    void editUser(User user);
    void registerNewUserAccount(UserDTO userDto) throws AppBaseException;
}
