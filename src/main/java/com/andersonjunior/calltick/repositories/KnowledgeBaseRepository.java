package com.andersonjunior.calltick.repositories;

import com.andersonjunior.calltick.models.KnowledgeBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long>{
    
}
