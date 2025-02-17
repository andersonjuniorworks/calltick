package com.andersonjunior.calltick.repositories;

import com.andersonjunior.calltick.models.Key;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, String> {
    
}
