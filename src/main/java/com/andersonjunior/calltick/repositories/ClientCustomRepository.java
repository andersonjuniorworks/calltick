package com.andersonjunior.calltick.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Contract;

import org.springframework.stereotype.Repository;

@Repository
public class ClientCustomRepository {

    private final EntityManager em;

    public ClientCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Client> find(String document, String fullname, String nickname, String city, Contract contract) {

        String query = "SELECT c FROM Client AS c ";
        String condition = "WHERE";

        if (document != null) {
            query += condition + " c.document = :document";
            condition = " AND ";
        }

        if (fullname != null) {
            query += condition + " lower(c.fullname) LIKE lower(concat('%', :fullname,'%'))";
            condition = " AND ";
        }

        if (nickname != null) {
            query += condition + " lower(c.nickname) LIKE lower(concat('%', :nickname,'%'))";
            condition = " AND ";
        }

        if (city != null) {
            query += condition + " lower(c.city) LIKE lower(concat('%', :city,'%'))";
        }
        
        if (contract != null) {
            query += condition + " c.contract = :contract";
        }

        TypedQuery<Client> qry = em.createQuery(query, Client.class);

        if (document != null) {
            qry.setParameter("document", document);
        }

        if (fullname != null) {
            qry.setParameter("fullname", "%"+fullname+"%");
        }

        if (nickname != null) {
            qry.setParameter("nickname", "%"+nickname+"%");
        }

        if (city != null) {
            qry.setParameter("city", "%"+city+"%");
        }

        if (contract != null) {
            qry.setParameter("contract", contract);
        }

        return qry.getResultList();

    }

    public Integer countByFilter(String document, String fullname, String nickname, String city, Contract contract) {

        String query = "SELECT c FROM Client AS c ";
        String condition = "WHERE";

        if (document != null) {
            query += condition + " c.document = :document";
            condition = " AND ";
        }

        if (fullname != null) {
            query += condition + " lower(c.fullname) LIKE lower(concat('%', :fullname,'%'))";
            condition = " AND ";
        }

        if (nickname != null) {
            query += condition + " lower(c.nickname) LIKE lower(concat('%', :nickname,'%'))";
            condition = " AND ";
        }

        if (city != null) {
            query += condition + " lower(c.city) LIKE lower(concat('%', :city,'%'))";
            condition = " AND ";
        }

        if (contract != null) {
            query += condition + " c.contract = :contract";
        }

        TypedQuery<Client> qry = em.createQuery(query, Client.class);

        if (document != null) {
            qry.setParameter("document", document);
        }

        if (fullname != null) {
            qry.setParameter("fullname", "%"+fullname+"%");
        }

        if (nickname != null) {
            qry.setParameter("nickname", "%"+nickname+"%");
        }

        if (city != null) {
            qry.setParameter("city", "%"+city+"%");
        }

        if (contract != null) {
            qry.setParameter("contract", contract);
        }

        return qry.getResultList().size();

    }

}
