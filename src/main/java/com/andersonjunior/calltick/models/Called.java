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

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Called")
@Table(name = "tb_calls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Called implements Serializable {

    @ApiModelProperty(value = "Código do chamado")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Cliente vinculado ao chamado")
    @JsonIgnore
    @ManyToOne
    private Client client;

    @ApiModelProperty(value = "Setor do chamado")
    @OneToOne
    private Sector sector;

    @ApiModelProperty(value = "Usuários responsáveis pelo chamado")
    @OneToMany(mappedBy = "called", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    @ApiModelProperty(value = "Data de abertura do chamado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date openingDate;

    @ApiModelProperty(value = "Data de fechamento do chamado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date closingDate;

    @ApiModelProperty(value = "Usuário que abriu o chamado")
    private String openBy;

    @ApiModelProperty(value = "Situação do chamado: 1 - Aberto; 2 - Pendente; 3 - Finalizado; 4 - Cancelado")
    private int status;

    @ApiModelProperty(value = "0 - Ativo; 1 - Inativo")
    private int active;
    
}
