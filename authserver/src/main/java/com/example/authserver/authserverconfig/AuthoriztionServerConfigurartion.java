package com.example.authserver.authserverconfig;

import com.example.authserver.service.ClientDetailService;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthoriztionServerConfigurartion extends AuthorizationServerConfigurerAdapter {

    @Autowired
    ClientDetailService clientDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetailsService);
        clients.inMemory().withClient("android").
        secret("android").authorizedGrantTypes("password","refresh_token").scopes("read").authorities("read");
    }
    @Bean
    public TokenStore getTokenStore()
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
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        MysqlDataSource tokenDataSource=new MysqlDataSource();
        tokenDataSource.setURL("jdbc:mysql://localhost:3306/tokenstore");
        tokenDataSource.setUser("root");
        tokenDataSource.setPassword("durga");

//        endpoints.authenticationManager(authenticationManager).tokenStore(new JdbcTokenStore(tokenDataSource));
          endpoints.authenticationManager(authenticationManager).tokenStore(getTokenStore()).accessTokenConverter(getAccessTokenConverter());
    }
}
