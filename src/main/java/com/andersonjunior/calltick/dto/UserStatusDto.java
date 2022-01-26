package com.andersonjunior.calltick.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.andersonjunior.calltick.models.Client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserStatusDto {

    @Id
    private Client client;
    private String status;

}
