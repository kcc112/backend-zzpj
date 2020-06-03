package com.zzpj.backend.api.v1;

import com.zzpj.backend.DTOs.UserDto;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @PostMapping("/registration")
    public String registerUserAccount(
            @RequestBody @Valid UserDto userDto, HttpServletRequest request) {
            try {
                User registered = userService.registerNewUserAccount(userDto);
            }
            catch (UserException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
            return "Registered";
    }

}
