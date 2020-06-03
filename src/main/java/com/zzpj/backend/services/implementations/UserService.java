package com.zzpj.backend.services.implementations;

import com.zzpj.backend.DTOs.UserDto;
import com.zzpj.backend.entities.User;
import com.zzpj.backend.exceptions.UserException;
import com.zzpj.backend.repositories.RoleRepository;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.services.interfaceses.UserServiceLocal;
import com.zzpj.backend.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceLocal {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(HashUtils.sha256(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void editUser(User user) {
        Optional<User> userDB = userRepository.findById(user.getId());
        if(userDB.isPresent()){
            userDB.get().setLogin(user.getLogin());
            userDB.get().setPassword(HashUtils.sha256(user.getPassword()));
            userRepository.save(userDB.get());
        }
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto userDto) throws UserException {
            if(emailExists(userDto.getLogin())){
                throw UserException.createExceptionEmailExists();
            }

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByLogin(email) != null;
    }
}
