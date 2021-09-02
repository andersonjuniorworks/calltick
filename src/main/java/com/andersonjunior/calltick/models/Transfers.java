package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_transfers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transfers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Called called;
    private String responsible;
    private String newResponsible;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTransfer;
    
}
