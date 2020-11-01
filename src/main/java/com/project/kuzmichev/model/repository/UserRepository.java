package com.project.kuzmichev.model.repository;

import com.project.kuzmichev.model.domain.user.User;
import com.project.kuzmichev.model.domain.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Integer> {
    Optional<User> findById(Integer id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findByUserRole(UserRole userRole);
}
