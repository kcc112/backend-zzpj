package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.entities.User;

public class UserMapper {
    public User mapUserDTOToUser(UserDTO userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
}