package com.zzpj.backend.api.v1;

import com.zzpj.backend.DTOs.AuthenticationResponse;
import com.zzpj.backend.DTOs.AuthenticationUserDto;
import com.zzpj.backend.DTOs.UserDto;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import com.zzpj.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    private UserServiceLocal userService;

    @Autowired
    public AuthenticationController(UserServiceLocal userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @RequestBody @Valid UserDto userDto, HttpServletRequest request) {
        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (AppBaseException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return "Success";
    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationUserDto authenticationUserDto)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationUserDto.getLogin(), authenticationUserDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationUserDto.getLogin());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
