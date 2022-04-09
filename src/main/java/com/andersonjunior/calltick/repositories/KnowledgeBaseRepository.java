package com.andersonjunior.calltick.repositories;

import java.util.List;

import com.andersonjunior.calltick.models.Category;
import com.andersonjunior.calltick.models.KnowledgeBase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KnowledgeBaseRepository extends JpaRepository<KnowledgeBase, Long>{
    
    List<KnowledgeBase> findByDescriptionContainsIgnoreCase(String description);

    List<KnowledgeBase> findByCategory(Category category);

}
