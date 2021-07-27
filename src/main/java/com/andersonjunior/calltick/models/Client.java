package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Client")
@Table(nme = "tb_clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer type;
    @Column(unique = true)
    private String cpfOrCnpj;
    private String fullname;
    private String nickname;
    private String zipcode;
    private String adress;
    private String homeNumber;
    private String complement;
    private State state;
    private City city;
    private String phoneNumberOne;
    private String phoneNumberTwo;
    private String email;
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    
}
