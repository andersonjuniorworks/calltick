package com.andersonjunior.calltick.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;

import org.springframework.stereotype.Repository;

@Repository
public class CalledCustomRepository {

    private final EntityManager em;

    public CalledCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Called> find(Client client, User user, Sector sector, Integer status) {

        String query = "SELECT c FROM Called AS c ";
        String condition = "WHERE";

        if (client != null) {
            query += condition + " c.client = :client";
            condition = " AND ";
        }

        if (user != null) {
            query += condition + " c.user = :user";
            condition = " AND ";
        }

        
        if (sector != null) {
            query += condition + " c.sector = :sector";
            condition = " AND ";
        }

        if (status != null) {
            query += condition + " c.status = :status";
        }

        TypedQuery<Called> qry = em.createQuery(query, Called.class);

        if (client != null) {
            qry.setParameter("client", client);
        }

        if (user != null) {
            qry.setParameter("user", user);
        }

        if (sector != null) {
            qry.setParameter("sector", sector);
        }

        if (status != null) {
            qry.setParameter("status", status);
        }

        return qry.getResultList();

    }

    public Integer count(Client client, User user, Sector sector, Integer status) {

        String query = "SELECT c FROM Called AS c ";
        String condition = "WHERE";

        if (client != null) {
            query += condition + " c.client = :client";
            condition = " AND ";
        }

        if (user != null) {
            query += condition + " c.user = :user";
            condition = " AND ";
        }

        if (sector != null) {
            query += condition + " c.sector = :sector";
            condition = " AND ";
        }

        if (status != null) {
            query += condition + " c.status = :status";
        }

        TypedQuery<Called> qry = em.createQuery(query, Called.class);

        if (client != null) {
            qry.setParameter("client", client);
        }

        if (user != null) {
            qry.setParameter("user", user);
        }

        if (sector != null) {
            qry.setParameter("sector", sector);
        }

        if (status != null) {
            qry.setParameter("status", status);
        }

        return qry.getResultList().size();

    }
    
}
