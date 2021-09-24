package com.andersonjunior.calltick.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.dto.ContractDto;
import com.andersonjunior.calltick.models.Contract;
import com.andersonjunior.calltick.repositories.ContractRepository;
import com.andersonjunior.calltick.services.exceptions.DataIntegrityException;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepo;

    public List<Contract> findAll() {
        return contractRepo.findAll();
    }

    public Contract findById(Long id) {
        Optional<Contract> obj = contractRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Contrato não encontrado na base de dados!!!"));
    }

    public Long count() {
        Long count = contractRepo.count();
        return count;
    }

    @Transactional
    public Contract insert(Contract obj) {
        obj.setId(null);
        return contractRepo.save(obj);
    }

    @Transactional
    public Contract update(Contract obj) {
        Contract newObj = findById(obj.getId());
        updateData(newObj, obj);
        return contractRepo.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            contractRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse contrato!");
        }
    }

    public Contract fromDTO(ContractDto objDto) {
        return new Contract(objDto.getId(), objDto.getDescription(), objDto.getPrice());
    }

    private void updateData(Contract newObj, Contract obj) {
        newObj.setDescription(obj.getDescription());
        newObj.setPrice(obj.getPrice());
    }

}
