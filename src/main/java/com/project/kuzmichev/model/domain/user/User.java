package com.project.kuzmichev.model.domain.user;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String firstName;
    @NotBlank
    private String secondName;
    @NotBlank
    private String patronymic;
    @NotBlank
    private String avatar;

    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    public boolean isAdmin(){
        return this.userRole.equals(UserRole.ADMIN);
    }

    public List<UserRole> getRoles(){
        return new ArrayList<UserRole>(Collections.singleton(userRole));
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", avatar='" + avatar + '\'' +
                ", birthday=" + birthday +
                ", userRole=" + userRole +
                '}';
    }
}