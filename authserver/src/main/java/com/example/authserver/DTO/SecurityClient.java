package com.example.authserver.DTO;

import com.example.authserver.entities.ClientPojo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
@Component
public class SecurityClient implements ClientDetails, Serializable {
    private ClientPojo clientPojo;

    public SecurityClient(ClientPojo clientPojo) {
        this.clientPojo = clientPojo;
    }

    @Override
    public String getClientId() {
        return clientPojo.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return false;
    }

    @Override
    public String getClientSecret() {
        return clientPojo.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set s=new HashSet();
        s.add(clientPojo.getScope());
        return s;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set s=new HashSet();
        String authorizedGrantTypes=clientPojo.getAuthorizedGrantTypes();
        s.add(authorizedGrantTypes);
        s.add("refresh_token");
        return s;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority= new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return clientPojo.getAuthorities();
            }
        };
        return Arrays.asList(grantedAuthority);
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return null;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return null;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
