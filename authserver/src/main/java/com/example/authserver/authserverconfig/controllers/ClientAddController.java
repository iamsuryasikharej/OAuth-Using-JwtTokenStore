package com.example.authserver.authserverconfig.controllers;

import com.example.authserver.entities.ClientPojo;
import com.example.authserver.repository.JpaClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientAddController {
    @Autowired
    JpaClientRepository jpaClientRepository;
    @GetMapping("/addClient")
    public void addClient()
    {
        ClientPojo clientPojo =new ClientPojo();
        clientPojo.setClientId("client1");
        clientPojo.setClientSecret("client1");
        clientPojo.setAuthorities("read");
        clientPojo.setScope("read");
        clientPojo.setAuthorizedGrantTypes("password");
        List a=new ArrayList();
        a.add(clientPojo);
        jpaClientRepository.saveAll(a);
    }
}
