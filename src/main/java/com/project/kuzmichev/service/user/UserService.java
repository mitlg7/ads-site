package com.project.kuzmichev.service.user;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.domain.user.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsersByRole(UserRole userRole);
    boolean createUser(User user);

}