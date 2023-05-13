package com.example.authserver.repository;

import com.example.authserver.entities.ClientPojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface JpaClientRepository extends JpaRepository<ClientPojo, Integer> {
    ClientPojo findClientPojoByClientId(String name);
//    void saveAll(List<ClientPojo> clientPojoList);



}
