package com.example.authserver.service;

import com.example.authserver.DTO.SecurityClient;
import com.example.authserver.entities.ClientPojo;
import com.example.authserver.repository.JpaClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class ClientDetailService implements org.springframework.security.oauth2.provider.ClientDetailsService {
    @Autowired
    JpaClientRepository jpaClientRepository;
    @Override
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        ClientPojo clientPojo=new ClientPojo();
        clientPojo.setClientId("android");
        clientPojo.setClientSecret("android");
        clientPojo.setAuthorizedGrantTypes("password");
        clientPojo.setScope("read");
        SecurityClient securityClient=new SecurityClient(clientPojo);

        return securityClient;
//        ClientPojo clientPojo=jpaClientRepository.findClientPojoByClientId(s);
//        System.out.println("==================");
//
//        System.out.println(s);
//
//        System.out.println(clientPojo.getClientId()+""+clientPojo.getClientSecret());
//        System.out.println("==================");
//
//        SecurityClient securityClient=new SecurityClient(clientPojo);
//
//        System.out.println("=================="+securityClient.getClientId()+""+securityClient.getClientSecret()+""+securityClient.getAuthorities()+""+securityClient.getScope()+""+securityClient.getAuthorizedGrantTypes());
//
//        return  securityClient;


    }
}
