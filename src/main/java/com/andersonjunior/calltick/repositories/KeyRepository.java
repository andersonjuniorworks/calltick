package com.andersonjunior.calltick.repositories;

import java.util.Date;
import java.util.List;

import com.andersonjunior.calltick.models.Key;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, Long> {
    
    List<Key> findByCreatedAtBetween(Date startDate, Date endDate);

}
