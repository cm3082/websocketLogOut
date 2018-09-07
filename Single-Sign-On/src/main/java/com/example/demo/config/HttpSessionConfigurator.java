package com.example.demo.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class HttpSessionConfigurator extends Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

    	//怎么搞？
    	HttpSession httpSession = (HttpSession) request.getHttpSession();
    	sec.getUserProperties().put(HttpSession.class.getName(), httpSession);
    }
}
