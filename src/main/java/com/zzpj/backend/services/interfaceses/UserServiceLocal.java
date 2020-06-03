package com.zzpj.backend.services.interfaceses;



import com.zzpj.backend.DTOs.UserDto;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;

import java.util.List;
import java.util.Optional;

public interface UserServiceLocal {

    void addUser(User user);
    Optional<User> getUser(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    void editUser(User user);
    User registerNewUserAccount(UserDto userDto) throws AppBaseException;
}
