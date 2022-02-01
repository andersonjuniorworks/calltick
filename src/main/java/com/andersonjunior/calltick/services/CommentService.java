package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.models.Called;
import com.andersonjunior.calltick.models.Comment;
import com.andersonjunior.calltick.repositories.CommentRepository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository technicalReportRepo;

    @Autowired
    public CommentService(CommentRepository technicalReportRepo) {
        this.technicalReportRepo = technicalReportRepo;
    }

    public List<Comment> findAll() {
        return technicalReportRepo.findAll();
    }

    public Comment findById(Long id) {
        Optional<Comment> obj = technicalReportRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Relato técnico não encontrado na base de dados!!!"));
    }

    public List<Comment> findByCalled(Called called) {
        List<Comment> list = technicalReportRepo.findByCalled(called);
        return list;
    }

    @Transactional
    public Comment insert(Comment obj) {
        obj.setId(null);
        return technicalReportRepo.save(obj);
    }
    
}
