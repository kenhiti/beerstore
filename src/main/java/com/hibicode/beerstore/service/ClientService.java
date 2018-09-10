package com.hibicode.beerstore.service;

import com.hibicode.beerstore.model.Client;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class ClientService {

    public List<Client> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8180/clientes")).build();

        ResponseEntity<List<Client>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Client>>() {});

        List<Client> body = response.getBody();

        body.forEach(c -> System.out.println(c.getNome()));

        return body;
    }
}
