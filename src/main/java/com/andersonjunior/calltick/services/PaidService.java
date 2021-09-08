package com.andersonjunior.calltick.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.andersonjunior.calltick.models.Paid;
import com.andersonjunior.calltick.repositories.PaidRepository;
import com.andersonjunior.calltick.services.exceptions.DataIntegrityException;
import com.andersonjunior.calltick.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaidService {

    @Autowired
    private PaidRepository paidRepo;

    public List<Paid> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return paidRepo.findAll(pageable).getContent();
    }

    public Paid findById(Long id) {
        Optional<Paid> obj = paidRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Pagamento não encontrado na base de dados!!!"));
    }

    public List<Paid> findByPeriod(Date startDate, Date endDate, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return paidRepo.findByDateOfPaymentBetween(startDate, endDate, pageable);
    }

    @Transactional
    public Paid insert(Paid obj) {
        obj.setId(null);
        return paidRepo.save(obj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            paidRepo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir esse pagamento!");
        }
    }

}
