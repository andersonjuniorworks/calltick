package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.models.KnowledgeBase;
import com.andersonjunior.calltick.repositories.KnowledgeBaseRepository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class KnowledgeBaseService {

    private final KnowledgeBaseRepository knowledgeBaseRepository;

    @Autowired
    public KnowledgeBaseService(KnowledgeBaseRepository knowledgeBaseRepository) {
        this.knowledgeBaseRepository = knowledgeBaseRepository;
    }

    public List<KnowledgeBase> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return knowledgeBaseRepository.findAll(pageable).getContent();
    }

    public KnowledgeBase findById(Long id) {
        Optional<KnowledgeBase> obj = knowledgeBaseRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum resultado encontrado na base de dados!!!"));
    }

    @Transactional
    public KnowledgeBase insert(KnowledgeBase knowledgeBase) {
        knowledgeBase.setId(null);
         return knowledgeBaseRepository.save(knowledgeBase);
    }

    @Transactional
    public KnowledgeBase update(KnowledgeBase obj) {
        KnowledgeBase newObj = findById(obj.getId());
        updateData(newObj, obj);
        return knowledgeBaseRepository.save(newObj);
    }

    public List<KnowledgeBase> findByDescription(String description) {
        return knowledgeBaseRepository.findByDescriptionContainsIgnoreCase(description);
    }

    private void updateData(KnowledgeBase newObj, KnowledgeBase obj) {
        newObj.setCategory(obj.getCategory());
        newObj.setTitle(obj.getTitle());
        newObj.setDescription(obj.getDescription());
        newObj.setCreatedAt(obj.getCreatedAt());
        newObj.setCreatedBy(obj.getCreatedBy());
        newObj.setUpdateAt(obj.getUpdateAt());
        newObj.setUpdateBy(obj.getUpdateBy());
    }

    
}
