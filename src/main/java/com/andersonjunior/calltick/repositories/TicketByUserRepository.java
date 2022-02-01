package com.andersonjunior.calltick.repositories;

import java.util.Date;
import java.util.List;

import com.andersonjunior.calltick.models.TicketByUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketByUserRepository extends JpaRepository<TicketByUser, Integer> {
    
    @Query(value = "SELECT COUNT(tc.id) AS quantity, tu.fullname AS description FROM tb_calls tc, tb_users tu WHERE tc.user_id = tu.id AND created_at BETWEEN :startDate AND :endDate GROUP BY tu.id", nativeQuery = true)
    List<TicketByUser> listTicketByUsers(Date startDate, Date endDate);

}
