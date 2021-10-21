package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.models.TechnicalReport;
import com.andersonjunior.calltick.repositories.TechnicalReportRepository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TechnicalReportService {

    private final TechnicalReportRepository technicalReportRepo;

    @Autowired
    public TechnicalReportService(TechnicalReportRepository technicalReportRepo) {
        this.technicalReportRepo = technicalReportRepo;
    }

    public List<TechnicalReport> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return technicalReportRepo.findAll(pageable).getContent();
    }

    public TechnicalReport findById(Long id) {
        Optional<TechnicalReport> obj = technicalReportRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Relato técnico não encontrado na base de dados!!!"));
    }

    @Transactional
    public TechnicalReport insert(TechnicalReport obj) {
        obj.setId(null);
        return technicalReportRepo.save(obj);
    }
    
}
