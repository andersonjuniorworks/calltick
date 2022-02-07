package com.andersonjunior.calltick.repositories;

import com.andersonjunior.calltick.models.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
