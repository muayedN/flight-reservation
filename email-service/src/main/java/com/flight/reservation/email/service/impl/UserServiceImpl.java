package com.flight.reservation.email.service.impl;

import com.flight.reservation.email.dto.UserResponse;
import com.flight.reservation.email.service.UserService;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class.getSimpleName());

    private final RestTemplate restTemplate;

    private final EurekaClient eurekaClient;

    @Value("${authenticationservice.name}")
    private String authenticationServiceName;

    @Value("${authenticationservice.jwt-token}")
    private String jwtToken;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, @Qualifier("eurekaClient") EurekaClient eurekaClient) {
        this.restTemplate = restTemplate;
        this.eurekaClient = eurekaClient;
    }

    @Override
    @HystrixCommand(fallbackMethod = "unableToFetchUserResponse")
    public UserResponse getUserWithPublicId(UUID publicId) {
        return restTemplate.exchange(urlForUserByPublicId(publicId), HttpMethod.GET, getAuthorizedHttpEntity(), UserResponse.class).getBody();
    }

    private String urlForUserByPublicId(UUID publicId) {
        return String.format("%s"+"account/user/%s",
                eurekaClient.getNextServerFromEureka(authenticationServiceName, false).getHomePageUrl(),
                publicId);
    }

    private UserResponse unableToFetchUserResponse(UUID uuid){
        logger.warning("Failed to get appropriate response from authentication service " + uuid);
        return new UserResponse();
    }

    private HttpEntity<String> getAuthorizedHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        headers.set("Authorization", "Bearer " + jwtToken);
        headers.setBearerAuth(jwtToken);

        return new HttpEntity<>("parameters", headers);
    }
}
