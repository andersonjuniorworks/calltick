package com.andersonjunior.calltick.repositories;

import java.util.List;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    @Transactional(readOnly=true)    
    Client findByDocument(String document);

    List<Client> findByFullnameContainingIgnoreCase(String fullname);
    
    List<Client> findByNicknameContainingIgnoreCase(String nickname);

    List<Client> findByCityIgnoreCase(String city);

    List<Client> findByContract(Contract contract);

    List<Client> findByCityAndContract(String city, Contract contract);

}
