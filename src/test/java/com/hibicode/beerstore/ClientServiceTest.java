package com.hibicode.beerstore;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.hibicode.beerstore.model.Client;
import com.hibicode.beerstore.service.ClientService;
import com.hibicode.beerstore.wiremock.WireMockConfig;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8180));

    @Autowired
    private ClientService service;

    private WireMockConfig wireMockConfig;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
       /* wireMockConfig = new WireMockConfig();
        wireMockConfig.init();*/
    }

    @After
    public void exit(){
        /*wireMockConfig.stop();*/
    }

    @Test
    public void clientTest1(){
        service = new ClientService();
        List<Client> all = service.findAll();
        Assert.assertFalse(all.isEmpty());

    }

    @Test
    public void clientTest2(){
        service = new ClientService();
        List<Client> all = service.findAll();
        Assert.assertFalse(all.isEmpty());
    }


    /* @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8180));*/

    @Test
    public void clientTest(){
        WireMockConfig wireMockConfig = new WireMockConfig();
        wireMockConfig.init();

        //configureFor("wiremock.host", 8089);
        //configureFor("tomcat.host", 8080, "/wiremock");

        //WireMock wireMock = new WireMock("localhost",8180); // As above, 3rd param is for non-root servlet deployments
       /* wireMock.register(get(urlEqualTo("/clientes/"))
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
*/
        /*stubFor(get(urlEqualTo("/clientes/"))
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
                )
        );
*/

        service = new ClientService();
       /* RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8180/clientes/")).build();
        ResponseEntity<List<Client>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<Client>>() {});*/

        List<Client> all = service.findAll();


        Assert.assertFalse(all.isEmpty());
        wireMockConfig.stop();

    }



}
