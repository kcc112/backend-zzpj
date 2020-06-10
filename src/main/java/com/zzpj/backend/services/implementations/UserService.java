package com.zzpj.backend.services.implementations;

import com.zzpj.backend.dto.UserDTO;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.AppBaseException;
import com.zzpj.backend.exceptions.UserException;
import com.zzpj.backend.repositories.RoleRepository;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserServiceLocal {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUser(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUser(User user) {
        User userDB = userRepository.findByLogin(user.getLogin());
        if(userDB != null){
            userDB.setFirstName(user.getFirstName());
            userDB.setLastName(user.getLastName());
            userDB.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(userDB);
        }
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDTO userDto) throws AppBaseException {
        if (emailExists(userDto.getLogin())) {
            throw UserException.createExceptionEmailExists();
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setLogin(userDto.getLogin());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByLogin(email) != null;
    }
}
