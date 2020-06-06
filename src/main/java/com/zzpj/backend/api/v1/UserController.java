package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.UserDTO;
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
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> get(@PathVariable UUID id) {
        Optional<User> user = userService.getUser(id);
        return new ResponseEntity<>(user.orElseGet(User::new), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody UserDTO userDTO) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.mapUserDTOToUser(userDTO);
        try {
            if (user.getUuid() != null) throw new AppBaseException();
            userService.addUser(user);
        } catch (AppBaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> edit(@RequestBody UserDTO userDTO) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.mapUserDTOToUser(userDTO);
        try {
            if (user.getUuid() != null) throw new AppBaseException();
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
