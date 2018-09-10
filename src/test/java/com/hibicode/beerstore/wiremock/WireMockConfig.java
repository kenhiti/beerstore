package com.hibicode.beerstore.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockConfig {

    private static WireMockServer wireMockServer;

    public WireMockConfig(){
        wireMockServer = new WireMockServer(wireMockConfig().port(8180));
    }

    public  void init(){
        wireMockServer.start();
    }

    public  void reset(){
        WireMock.reset();
    }

    public  void stop(){
        wireMockServer.stop();
    }
}
