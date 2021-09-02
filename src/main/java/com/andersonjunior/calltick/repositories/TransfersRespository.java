package com.andersonjunior.calltick.repositories;

import com.andersonjunior.calltick.models.Transfers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfersRespository extends JpaRepository<Transfers, Long>{
    
}
