package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.dto.UserInfoDTO;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.mappers.UserMapper;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceLocal userService;

    @Autowired
    public UserController(UserServiceLocal userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserInfoDTO> getAll() {
        UserMapper userMapper = new UserMapper();
        return userMapper.mapUserToUserInfoDTOList(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserInfoDTO> get(@PathVariable UUID id) {
        UserMapper userMapper = new UserMapper();
        UserInfoDTO user = userMapper.mapUserToUserInfoDTO(userService.getUser(id).orElseGet(User::new));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> edit(@RequestBody UserDTO userDto) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.mapUserDTOToUser(userDto);
        try {
            if (user.getLogin().isEmpty()) throw new AppBaseException("Invalid data");
            userService.editUser(user);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
