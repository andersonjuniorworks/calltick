package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_company")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer type;
    private String document;
    private String stateRegistration;
    private String fullname;
    private String nickname;
    private String zipcode;
    private String address;
    private String homeNumber;
    private String neighborhood;
    private String complement;
    private String state;
    private String city;
    private String phoneNumberOne;
    private String phoneNumberTwo;
    private String email;
    private String logo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

}
