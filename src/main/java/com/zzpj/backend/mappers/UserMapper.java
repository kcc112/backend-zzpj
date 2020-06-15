package com.zzpj.backend.mappers;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.dto.UserInfoDTO;
import com.zzpj.backend.entities.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserMapper {
    public User mapUserDTOToUser(UserDTO userDto) {
        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }

    public UserInfoDTO mapUserToUserInfoDTO(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFirstName(user.getFirstName());
        userInfoDTO.setLastName(user.getLastName());
        userInfoDTO.setLogin(user.getLogin());
        return userInfoDTO;
    }

    public List<UserInfoDTO> mapUserToUserInfoDTOList(List<User> users) {
        List<UserInfoDTO> userInfoDTOS = new ArrayList<>();
        for(User user : users
            ){
            userInfoDTOS.add(mapUserToUserInfoDTO(user));
        }
        return userInfoDTOS;
    }
}