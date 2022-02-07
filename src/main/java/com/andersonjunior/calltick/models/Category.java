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
@Table(name = "tb_categories")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @OneToOne
    private User updateBy;
    
}
