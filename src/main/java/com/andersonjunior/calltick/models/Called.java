package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "Called")
@Table(name = "tb_calls")
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
    @Column(columnDefinition = "LONGTEXT")
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
    @OneToMany(mappedBy = "called")
    private List<TechnicalReport> technicalReports;

    @ApiModelProperty(value = "Situação do chamado: 1 - Aberto; 2 - Pendente; 3 - Finalizado; 4 - Cancelado")
    private int status;

    @ApiModelProperty(value = "0 - Ativo; 1 - Inativo")
    private int active;

    @ApiModelProperty(value = "Data do registro")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public Called() {

    }

    public Called(Long id, Client client, int typeService, Sector sector, String subject, String description, User user,
            String openingDate, String closingDate, String openBy, String closeBy, int status, int active,
            Date createdAt) {
        this.id = id;
        this.client = client;
        this.typeService = typeService;
        this.sector = sector;
        this.subject = subject;
        this.description = description;
        this.user = user;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.openBy = openBy;
        this.closeBy = closeBy;
        this.status = status;
        this.active = active;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getTypeService() {
        return typeService;
    }

    public void setTypeService(int typeService) {
        this.typeService = typeService;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(String closingDate) {
        this.closingDate = closingDate;
    }

    public String getOpenBy() {
        return openBy;
    }

    public void setOpenBy(String openBy) {
        this.openBy = openBy;
    }

    public String getCloseBy() {
        return closeBy;
    }

    public void setCloseBy(String closeBy) {
        this.closeBy = closeBy;
    }

    public List<TechnicalReport> getTechnicalReports() {
        return technicalReports;
    }

    public void setTechnicalReports(List<TechnicalReport> technicalReports) {
        this.technicalReports = technicalReports;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
}
