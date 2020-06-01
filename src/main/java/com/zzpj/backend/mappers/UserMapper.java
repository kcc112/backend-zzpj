package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.entities.User;

public class UserMapper {
    public User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setType(userDTO.getType());
        return user;
    }
}