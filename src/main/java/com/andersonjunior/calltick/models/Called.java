package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Called")
@Table(name = "calls")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Called implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonIgnore
    @ManyToOne
    private Client client;
    @OneToOne
    private Sector sector;
    @OneToMany(mappedBy = "called", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;
    private String openBy;
    private int status;
    
}
