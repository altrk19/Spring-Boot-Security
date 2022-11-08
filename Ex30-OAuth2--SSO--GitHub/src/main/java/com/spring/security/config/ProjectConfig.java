package com.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public ClientRegistrationRepository clientRepository(){
        ClientRegistration clientRegistration = clientRegistration();
        return new InMemoryClientRegistrationRepository(clientRegistration);
    }

    private ClientRegistration clientRegistration() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .clientId("c6a2117340641d5a8ae3")
                .clientSecret("5ada8280954ac8d96bf9ea267ddf55cc1858a86d")
                .build();
        // We can also set these values in application.properties
    }

    //    private ClientRegistration clientRegistration() {
//        ClientRegistration cr = ClientRegistration.withRegistrationId("github")
//                .clientId("a7553955a0c534ec5e6b")
//                .clientSecret("1795b30b425ebb79e424afa51913f1c724da0dbb")
//                .scope(new String[]{"read:user"})
//                .authorizationUri("https://github.com/login/oauth/authorize")
//                .tokenUri("https://github.com/login/oauth/access_token")
//                .userInfoUri("https://api.github.com/user")
//                .userNameAttributeName("id")
//                .clientName("GitHub")
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUriTemplate("{baseUrl}/{action}/oauth2/code/{registrationId}")
//                .build();
//        return cr;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.oauth2Login();

        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}
