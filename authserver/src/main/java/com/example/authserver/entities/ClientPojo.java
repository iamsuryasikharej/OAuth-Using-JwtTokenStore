package com.example.authserver.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class ClientPojo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public long getId() {
        return id;
    }

    private String authorizedGrantTypes;
    private String scope;
    private String clientId;
    private String clientSecret;
    private String authorities;

    public String getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    public String getScope() {
        return this.scope;
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public String getAuthorities() {
        return this.authorities;
    }
}
