package com.zzpj.backend.services;

import com.zzpj.backend.entities.User;
import com.zzpj.backend.repositories.UserRepository;
import com.zzpj.backend.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService implements UserServiceLocal {

    private UserRepository userRepository;

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
            userDB.get().setType(user.getType());
            userRepository.save(userDB.get());
        }
    }
}
