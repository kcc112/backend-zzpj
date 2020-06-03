package com.zzpj.backend.api.v1;

import com.zzpj.backend.DTOs.UserDto;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    private UserServiceLocal userService;

    @Autowired
    public RegistrationController(UserServiceLocal userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        try {
            User registered = userService.registerNewUserAccount(userDto);
        }
        catch (AppBaseException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return "Success";
    }
}
