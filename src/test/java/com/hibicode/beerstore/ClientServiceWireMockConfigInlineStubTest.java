package com.hibicode.beerstore;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.hibicode.beerstore.model.Client;
import com.hibicode.beerstore.service.ClientService;
import com.hibicode.beerstore.wiremock.WireMockConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceWireMockConfigInlineStubTest {

    @Autowired
    private ClientService service;

    private WireMockConfig wireMockConfig;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        wireMockConfig = new WireMockConfig();
        wireMockConfig.init();
    }

    @After
    public void exit(){
        wireMockConfig.stop();
    }

    @Test
    public void clienteTest(){
        WireMock wireMock = new WireMock("localhost",8180);
        wireMock.register(get(urlEqualTo("/clientes"))
                .willReturn(
                        aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("[\n" +
                                        "    {\n" +
                                        "        \"idCliente\": 99,\n" +
                                        "        \"nome\": \"Wiremock\",\n" +
                                        "        \"dataNascimento\": \"1984-10-19\",\n" +
                                        "        \"cpf\": \"11111111111\",\n" +
                                        "        \"endereco\": \"Rua Um, 75\"\n" +
                                        "    }\n" +
                                        "]")
                ));
        service = new ClientService();
        List<Client> all = service.findAll();
        Assert.assertFalse(all.isEmpty());
    }
}
