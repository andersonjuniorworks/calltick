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
    private Date openingDate;
    private Date closingDate;
    private String openBy;
    private String closeBy;
    private String technicalReport;
    private int status;
    private int active;
    private Date createdAt;
  
}
