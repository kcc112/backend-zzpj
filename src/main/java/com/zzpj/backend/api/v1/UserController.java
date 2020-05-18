package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.User;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("users")
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
    public User get(@PathVariable Long id){
        Optional<User> user = userService.getUser(id);
        if(user.isPresent()){
            return user.get();
        }
        return new User();
    }

    @PostMapping
    public String add(@RequestBody User user){
        userService.addUser(user);
        return "Success";
    }

    @PutMapping
    public String edit(@RequestBody User user){
        userService.editUser(user);
        return "Success";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id){
        userService.deleteUser(id);
        return "Success";
    }
}
