package com.andersonjunior.calltick.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "tb_knowledge_base")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class KnowledgeBase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Category category;

    @Column(length = 300)
    private String title;

    @Column(columnDefinition = "LONGTEXT")
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
