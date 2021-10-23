package com.andersonjunior.calltick.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CompanyDto implements Serializable {

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
