package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.SectorDto;
import com.andersonjunior.calltick.models.Sector;
import com.andersonjunior.calltick.repositories.SectorRepository;
import com.andersonjunior.calltick.services.exceptions.DataIntegrityException;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepo;

    public List<Sector> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return sectorRepo.findAll(pageable).getContent();
    }

    public Sector findById(Integer id) {
        Optional<Sector> obj = sectorRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Setor não encontrado na base de dados!!!"));
    }

    public List<Sector> findByDescription(String description) {
        return sectorRepo.findByDescription(description);
    }

    @Transactional
    public Sector insert(Sector obj) {
        obj.setId(null);
        return sectorRepo.save(obj);
    }

    @Transactional
    public Sector update(Sector obj) {
        Sector newObj = findById(obj.getId());
        updateData(newObj, obj);
        return sectorRepo.save(newObj);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            sectorRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse setor!");
        }
    }

    public Sector fromDTO(SectorDto objDto) {
        return new Sector(objDto.getId(), objDto.getDescription());
    }

    private void updateData(Sector newObj, Sector obj) {
        newObj.setDescription(obj.getDescription());
    }

}
