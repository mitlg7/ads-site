package com.project.kuzmichev.model.domain.ad;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
public class Ad  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String address;

    @NotBlank
    @Column(length = 2048)
    private String description;

    @NotNull
    private float cost;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 2048)
    private String photo;

    @Enumerated(EnumType.STRING)
    private AdStatus adStatus;

    
    @Enumerated(EnumType.STRING)
    private AdCategory adCategory;


    @Enumerated(EnumType.STRING)
    private AdType adType;

    @Override
    public String toString() {
        return "Ad{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", date=" + date +
                ", photo='" + photo + '\'' +
                ", adStatus=" + adStatus +
                ", adCategory=" + adCategory +
                ", adType=" + adType +
                '}';
    }
}