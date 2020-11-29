package com.project.kuzmichev.model.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "responses")
@Getter
@Setter
@NoArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private int idAd;
    @NotBlank
    private String username;
    @NotBlank
    private String phone;
    private boolean read;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column(length = 2048)
    @NotBlank
    private String message;
}
