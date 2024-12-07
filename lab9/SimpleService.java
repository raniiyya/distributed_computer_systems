package com.example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SimpleService {

    @WebMethod
    public String printMessage(String message) {
        return "Message received: " + message;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/SimpleService", new SimpleService());
        System.out.println("Service deployed at http://localhost:8080/SimpleService?wsdl");
    }
}
