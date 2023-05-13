package com.example.resserver.resserverconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    DataSource ds;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/hello")
                .hasAuthority("read");
    }
    @Bean
    public JwtTokenStore getTokenStore()
    {
        return new JwtTokenStore(getAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter getAccessTokenConverter()
    {
        JwtAccessTokenConverter accessTokenConverter=new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("secret");
        return accessTokenConverter;
    }

}
