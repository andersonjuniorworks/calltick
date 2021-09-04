package com.andersonjunior.calltick.repositories;

import java.util.List;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalledRepository extends JpaRepository<Called, Long>{
    
    @Query(value = "SELECT c FROM Called c WHERE c.client = :client AND c.status = :status")
    List<Called> findByClient(Client client, int status, Pageable pageable);

    List<Called> findByOpeningDateBetween(String startDate, String endDate, Pageable pageable);

    List<Called> findByActive(Integer active, Pageable pageable);

    List<Called> findByStatusAndActive(Integer status, Integer active, Pageable pageable);

}
