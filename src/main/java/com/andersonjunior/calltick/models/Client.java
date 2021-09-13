package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Client")
@Table(name = "tb_clients")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Client implements Serializable {

    @ApiModelProperty(value = "Código do cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(value = "1 - Pessoa Física; 2 - Pessoa Juridíca")
    private Integer type;

    @ApiModelProperty(value = "CPF ou CNPJ do Cliente")
    @Column(unique = true)
    private String cpfOrCnpj;

    @ApiModelProperty(value = "Nome ou Razão Social")
    private String fullname;

    @ApiModelProperty(value = "Apelido ou Nome Fantasia")
    private String nickname;

    @ApiModelProperty(value = "CEP")
    private String zipcode;

    @ApiModelProperty(value = "Endereço")
    private String address;

    @ApiModelProperty(value = "Número da residência/estabelecimento")
    private String homeNumber;

    @ApiModelProperty(value = "Complemento")
    private String complement;

    @ApiModelProperty(value = "Estado")
    private String state;

    @ApiModelProperty(value = "Cidade")
    private String city;

    @ApiModelProperty(value = "Telefone 1")
    private String phoneNumberOne;

    @ApiModelProperty(value = "Telefone 2")
    private String phoneNumberTwo;

    @ApiModelProperty(value = "Email")
    private String email;

    @ApiModelProperty(value = "Data de cadastro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    
}
