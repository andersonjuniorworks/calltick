package com.andersonjunior.calltick.repositories;

import com.andersonjunior.calltick.models.TechnicalReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalReportRepository extends JpaRepository<TechnicalReport, Long> {
    
}
