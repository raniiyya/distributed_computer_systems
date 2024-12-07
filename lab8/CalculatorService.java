package com.example;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculatorService {

    @WebMethod
    public int add(int a, int b) {
        return a + b;
    }

    @WebMethod
    public double[] generateSequence(int n, double start, double end) {
        double[] sequence = new double[n];
        double step = (end - start) / (n - 1);
        for (int i = 0; i < n; i++) {
            sequence[i] = 0.5 * Math.log10(start + i * step);
        }
        return sequence;
    }

    @WebMethod
    public double[] disturbSequence(double[] sequence, double stdDev) {
        java.util.Random random = new java.util.Random();
        double[] disturbed = new double[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            disturbed[i] = sequence[i] + stdDev * random.nextGaussian();
        }
        return disturbed;
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/CalculatorService", new CalculatorService());
        System.out.println("Service deployed at http://localhost:8080/CalculatorService?wsdl");
    }
}
