package com.project.kuzmichev.model.domain.user;

import com.project.kuzmichev.model.domain.ad.Ad;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String avatar;

    @Temporal(value = TemporalType.DATE)
    private Date birthday;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Ad> ads = new ArrayList<>();

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





}