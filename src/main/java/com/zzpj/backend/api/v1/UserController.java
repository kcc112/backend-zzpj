package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.mappers.UserMapper;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserServiceLocal userService;

    @Autowired
    public UserController(UserServiceLocal userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        return user.orElseGet(User::new);
    }

    @PostMapping
    public String add(@RequestBody UserDTO userDTO) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.mapUserDTOToUser(userDTO);
        user.setId(null);

        userService.addUser(user);
        return "Success";
    }

    @PutMapping
    public String edit(@RequestBody UserDTO userDTO) {
        UserMapper userMapper = new UserMapper();
        User user = userMapper.mapUserDTOToUser(userDTO);

        userService.editUser(user);
        return "Success";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return "Success";
    }
}
