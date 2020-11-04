package com.project.kuzmichev.model.domain.ad;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private int userId;
    private String name;
    private String address;

    @Column(length = 2048)
    private String description;

    private float cost;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ElementCollection
    @OrderColumn(name = "ad_id")
    private Set<String> photos = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private AdStatus adStatus;

    @Enumerated(EnumType.STRING)
    private AdCategory adCategory;

    @Enumerated(EnumType.STRING)
    private AdType adType;




}