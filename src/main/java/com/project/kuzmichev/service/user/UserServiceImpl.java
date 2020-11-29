package com.project.kuzmichev.service.user;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.domain.user.UserRole;
import com.project.kuzmichev.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsersByRole(UserRole userRole) {
        return userRepository.findByUserRole(userRole);
    }

    @Override
    public boolean createUser(User user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean save(User user) {
        userRepository.save(user);
        return true;
    }

}