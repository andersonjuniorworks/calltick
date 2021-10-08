package com.andersonjunior.calltick.repositories;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.andersonjunior.calltick.component.CaptureIntervalDate;
import com.andersonjunior.calltick.component.DataConverter;
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

    public List<Called> find(Client client, User user, Sector sector, Integer status, Date startDate, Date endDate) {

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
            condition = " AND ";
        }

        if(startDate != null && endDate != null) {
            query += condition + " c.createdAt BETWEEN :startDate AND :endDate";
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
            qry.setParameter("startDate", startDate);
            qry.setParameter("endDate", endDate);
        }

        return qry.getResultList();

    }

    public Integer count(Client client, User user, Sector sector, Integer status) throws ParseException {

        Date startDate = new DataConverter().parseDate(new CaptureIntervalDate().getFirstDayOfMonth());
        Date endDate = new DataConverter().parseDate(new CaptureIntervalDate().getLastDayOfMonth());

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
            condition = " AND ";
        }

        if(startDate != null && endDate != null) {
            query += condition + " c.createdAt BETWEEN :startDate AND :endDate";
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
            qry.setParameter("startDate", startDate);
            qry.setParameter("endDate", endDate);
        }

        return qry.getResultList().size();

    }
    
}
