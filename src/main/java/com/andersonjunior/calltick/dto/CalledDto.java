package com.andersonjunior.calltick.dto;

import java.io.Serializable;
import java.sql.Date;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CalledDto implements Serializable {

    private Long id;
    private Client client;
    private int typeService;
    private Sector sector;
    private String subject;
    private String description;
    private User user;
    private String openingDate;
    private String closingDate;
    private String openBy;
    private String closeBy;
    private int status;
    private String technicalReport;
    private int active;
    private Date createdAt;
  
}
