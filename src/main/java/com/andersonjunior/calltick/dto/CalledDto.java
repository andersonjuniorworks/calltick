package com.andersonjunior.calltick.dto;

import java.io.Serializable;
import java.util.Date;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CalledDto implements Serializable {

    private Long id;

    private Client client;

    private Sector sector;

    private User user;

    private Date openingDate;

    private Date closingDate;
    private String openBy;
    private int status;
    private int active;
    
}
