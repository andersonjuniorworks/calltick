package com.andersonjunior.calltick.repositories;

import java.util.Date;
import java.util.List;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Paid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaidRepository extends JpaRepository<Paid, Long> {

    List<Paid> findByDateOfPaymentBetween(Date startDate, Date endDate, Pageable pageable);
    List<Paid> findByClient(Client client);

}
