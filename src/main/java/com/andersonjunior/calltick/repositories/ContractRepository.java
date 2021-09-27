package com.andersonjunior.calltick.repositories;

import java.util.Optional;

import com.andersonjunior.calltick.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Optional<Contract> findById(Long id);
  
}
