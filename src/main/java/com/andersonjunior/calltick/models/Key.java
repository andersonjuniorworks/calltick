package com.andersonjunior.calltick.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Key")
@Table(name = "tb_keys")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Key implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String key;

}
