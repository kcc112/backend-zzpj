package com.zzpj.backend.api.v1;

import com.zzpj.backend.dto.AuthenticationResponse;
import com.zzpj.backend.dto.AuthenticationUserDTO;
import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;
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

import javax.security.auth.login.AccountException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private JwtUtil jwtUtil;

    private UserServiceLocal userService;

    @Autowired
    public AuthenticationController(UserServiceLocal userService, AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @RequestBody @Valid UserDTO userDTO) {
        try {
            userService.registerNewUserAccount(userDTO);
        } catch (AppBaseException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        return "Success";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@Valid @RequestBody AuthenticationUserDTO authenticationUserDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationUserDTO.getLogin(), authenticationUserDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, UserException.createExceptionIncorrectCredentials(e).getMessage(), UserException.createExceptionIncorrectCredentials(e));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationUserDTO.getLogin());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
