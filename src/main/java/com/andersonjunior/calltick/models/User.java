package com.andersonjunior.calltick.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "tb_users")
@NoArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullname;
    @Column(unique = true)
    private String email;
    @JsonIgnore
    private String password;
    private Integer profile;
    @JsonIgnore
    @ManyToOne
    private Called called;

    public User(Integer id, String fullname, String email, String password, Integer profile) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

}
