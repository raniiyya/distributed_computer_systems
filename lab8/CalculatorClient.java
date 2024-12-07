package com.example.client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class CalculatorClient {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/CalculatorService?wsdl");
            QName qname = new QName("http://example.com/", "CalculatorService");
            Service service = Service.create(url, qname);
            CalculatorService calculator = service.getPort(CalculatorService.class);

            System.out.println("Result of adding 5 and 10: " + calculator.add(5, 10));

            double[] sequence = calculator.generateSequence(10, 1, 3);
            System.out.println("Generated Sequence:");
            for (double value : sequence) {
                System.out.println(value);
            }

            double[] disturbedSequence = calculator.disturbSequence(sequence, 0.1);
            System.out.println("Disturbed Sequence:");
            for (double value : disturbedSequence) {
                System.out.println(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
