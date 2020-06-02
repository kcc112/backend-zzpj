package com.zzpj.backend.api.v1;

import com.zzpj.backend.entities.User;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User get(@PathVariable Long id) {
        Optional<User> user = userService.getUser(id);
        return user.orElseGet(User::new);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody User user) {
        try {
            if(user.getId() != null && user.getId() < 0) throw new Exception();
            userService.addUser(user);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity edit(@RequestBody User user) {
        try {
            if(user.getId() != null && user.getId() < 0) throw new Exception();
            userService.editUser(user);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            if(id < 0) throw new Exception();
            userService.deleteUser(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
