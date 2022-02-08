package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.models.Category;
import com.andersonjunior.calltick.repositories.CategoryRepository;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return categoryRepository.findAll(pageable).getContent();
    }

    public Category findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhum resultado encontrado na base de dados!!!"));
    }

    @Transactional
    public Category insert(Category category) {
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(Category obj) {
        Category newObj = findById(obj.getId());
        updateData(newObj, obj);
        return categoryRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        if (id != 1) {
            try {
                categoryRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException("Não é possível excluir esta categoria!");
            }
        } else {
            throw new DataIntegrityViolationException("Não é possível excluir a categoria padrão!");
        }
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setDescription(obj.getDescription());
        newObj.setCreatedAt(obj.getCreatedAt());
        newObj.setCreatedBy(obj.getCreatedBy());
        newObj.setUpdateAt(obj.getUpdateAt());
        newObj.setUpdateBy(obj.getUpdateBy());
    }
    
}
