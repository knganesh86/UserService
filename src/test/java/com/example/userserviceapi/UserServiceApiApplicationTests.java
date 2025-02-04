package com.example.userserviceapi;

import com.example.userserviceapi.security.repos.JpaRegisteredClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.UUID;

@SpringBootTest
class UserServiceApiApplicationTests {


	@Autowired
	JpaRegisteredClientRepository registeredClientRepository;

	@Test
	void contextLoads() {
	}

    @Test
    public void addSampleRegisteredClient() {
//        RegisteredClient oidcClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("testClient")
//                .clientSecret("$2a$12$3D2WQY9rWan5zGRkzfLucO86d8nYjq5Etw1LWuYI7hA6ltH8CEYg2")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .redirectUri("https://oauth.pstmn.io/v1/callback")
//                .postLogoutRedirectUri("https://oauth.pstmn.io/v1/callback")
//                .scope("ADMIN")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        registeredClientRepository.save(oidcClient);
    }

}
