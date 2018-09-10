package com.hibicode.beerstore;

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

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceWireMockConfigStubInFileTest {

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
    public void clientTest(){
        service = new ClientService();
        List<Client> all = service.findAll();
        Assert.assertFalse(all.isEmpty());

    }

}
