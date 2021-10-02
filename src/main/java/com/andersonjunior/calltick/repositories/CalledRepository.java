package com.andersonjunior.calltick.repositories;

import java.util.Date;
import java.util.List;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.models.User;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalledRepository extends JpaRepository<Called, Long>{
    
    @Query(value = "SELECT c FROM Called c WHERE c.client = :client AND c.status = :status")
    List<Called> findByClient(Client client, int status, Pageable pageable);
    List<Called> findByClientAndUserAndStatus(Client client, User user, int status, Pageable pageable);
    List<Called> findByClientAndSectorAndStatus(Client client, Sector sector, int status, Pageable pageable);
    List<Called> findByClientAndUserAndSectorAndStatus(Client client, User user, Sector sector, int status, Pageable pageable);

    List<Called> findBySectorAndStatus(Sector sector, int status, Pageable pageable);
        
    List<Called> findByUserAndStatus(User user, Integer status);
    List<Called> findByUserAndStatusAndActive(User user, Integer status, Integer active, Pageable pageable);

    List<Called> findByStatusAndActiveOrderByIdDesc(Integer status, Integer active, Pageable pageable);

    List<Called> findByActive(Integer active, Pageable pageable);

    @Query(value = "SELECT c FROM Called c ORDER BY c.id DESC")
    List<Called> findCalls(Pageable pageable);

}
