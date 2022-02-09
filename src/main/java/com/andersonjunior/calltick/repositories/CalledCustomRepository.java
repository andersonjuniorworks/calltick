package com.andersonjunior.calltick.repositories;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;
import com.andersonjunior.calltick.utils.DataConverter;

import org.springframework.stereotype.Repository;

@Repository
public class CalledCustomRepository {

    private final EntityManager em;

    public CalledCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<Called> find(Client client, User user, Sector sector, Integer status, String startDate, String endDate) throws ParseException {

        String query = "SELECT c FROM Called AS c WHERE c.status = :status";

        if (client != null) {
            query += " AND c.client = :client";
        }

        if (user != null) {
            query += " AND c.user = :user";
        }
 
        if (sector != null) {
            query += " AND c.sector = :sector";
        }

        if(startDate != null && endDate != null) {
            query += " AND c.createdAt BETWEEN :startDate AND :endDate";
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

        if (startDate != null && endDate != null) {
            qry.setParameter("startDate", new DataConverter().parseDate(startDate));
            qry.setParameter("endDate", new DataConverter().parseDate(endDate));
        }

        return qry.getResultList();

    }

    public Integer count(Client client, User user, Sector sector, Integer status, String startDate, String endDate) throws ParseException {

        String query = "SELECT c FROM Called AS c WHERE c.createdAt BETWEEN :startDate AND :endDate";
       
        if (client != null) {
            query += " AND c.client = :client";
        }

        if (user != null) {
            query += " AND c.user = :user";
        }

        if (sector != null) {
            query += " AND c.sector = :sector";
        }

        if (status != null) {
            query += " AND c.status = :status";
        }

        if(startDate != null && endDate != null) {
            query += " AND c.createdAt BETWEEN :startDate AND :endDate";
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

        if (startDate != null && endDate != null) {
            qry.setParameter("startDate", new DataConverter().parseDate(startDate));
            qry.setParameter("endDate", new DataConverter().parseDate(endDate));
        }

        return qry.getResultList().size();

    }
    
}
