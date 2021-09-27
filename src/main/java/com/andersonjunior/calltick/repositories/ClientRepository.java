package com.andersonjunior.calltick.repositories;

import java.util.List;
import java.util.Optional;

import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.Contract;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByDocument(String cpfOrCnpj);

    List<Client> findByFullnameContainingIgnoreCase(String fullname);
    
    List<Client> findByNicknameContainingIgnoreCase(String nickname);

    List<Client> findByCityIgnoreCase(String city);

    List<Client> findByContract(Contract contract);

}
