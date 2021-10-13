package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @ManyToOne
    private Client client;

    @ApiModelProperty(value = "Tipo de Atendimento: 1 - Interno; 2 - Externo; 3 - Remoto")
    private int typeService;

    @ApiModelProperty(value = "Setor do chamado")
    @OneToOne
    private Sector sector;

    @ApiModelProperty(value = "Assunto do chamado")
    private String subject;

    @ApiModelProperty(value = "Descrição da solicitação do cliente")
    private String description;

    @ApiModelProperty(value = "Usuário responsável pelo chamado")
    @OneToOne
    private User user;

    @ApiModelProperty(value = "Data de abertura do chamado")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private String openingDate;

    @ApiModelProperty(value = "Data de fechamento do chamado")
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    private String closingDate;

    @ApiModelProperty(value = "Usuário que abriu o chamado")
    private String openBy;

    @ApiModelProperty(value = "Usuário que fechou o chamado")
    private String closeBy;

    @ApiModelProperty(value = "Relato técnico ao finalizar o chamado")
    private String technicalReport;

    @ApiModelProperty(value = "Situação do chamado: 1 - Aberto; 2 - Pendente; 3 - Finalizado; 4 - Cancelado")
    private int status;

    @ApiModelProperty(value = "0 - Ativo; 1 - Inativo")
    private int active;

    @ApiModelProperty(value = "Data do registro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    
}
