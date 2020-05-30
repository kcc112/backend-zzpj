package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.User;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public User get(@PathVariable Long id){
        Optional<User> user = userService.getUser(id);
        return user.orElseGet(User::new);
    }

    @PostMapping
    public HttpStatus add(@RequestBody User user){
        try {
        userService.addUser(user);
    } catch (Exception e) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
        return HttpStatus.CREATED;
    }

    @PutMapping
    public HttpStatus edit(@RequestBody User user){
        try {
            userService.editUser(user);
    } catch (Exception e) {
        return HttpStatus.NOT_MODIFIED;
    }
        return HttpStatus.OK;
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable Long id){
       try {
           userService.deleteUser(id);
    } catch (Exception e) {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
        return HttpStatus.OK;
    }
}
