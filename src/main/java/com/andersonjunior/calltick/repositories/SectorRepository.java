package com.andersonjunior.calltick.repositories;

import java.util.List;

import com.andersonjunior.calltick.models.Sector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer>{
    
    @Query(value = "SELECT s FROM Sector s WHERE s.description LIKE %:description% ORDER BY s.description ASC")
    List<Sector> findByDescription(String description);

}
