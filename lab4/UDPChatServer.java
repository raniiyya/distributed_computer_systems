import java.net.*;
import java.io.*;

public class UDPChatServer {
    static final int INPORT = 6666;
    private DatagramSocket mysocket;
    private DatagramPacket dp;
    private byte[] inbuf = new byte[1000];
    private int clientCount = 0;
    private InetAddress[] clientAddresses = new InetAddress[2];
    private int[] clientPorts = new int[2];

    public UDPChatServer() {
        try {
            mysocket = new DatagramSocket(INPORT);
            System.out.println("The chat server is up!");
            while (clientCount < 2) {
                dp = new DatagramPacket(inbuf, inbuf.length);
                mysocket.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());

                if (clientCount < 2) {
                    clientAddresses[clientCount] = dp.getAddress();
                    clientPorts[clientCount] = dp.getPort();
                    clientCount++;
                    System.out.println("Client connected: " + message);
                    mysocket.send(new DatagramPacket("Connected".getBytes(), "Connected".length(), dp.getAddress(), dp.getPort()));
                }
            }
            System.out.println("Two clients connected. Start chatting!");

            while (true) {
                dp = new DatagramPacket(inbuf, inbuf.length);
                mysocket.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());

                int senderIndex = (dp.getAddress().equals(clientAddresses[0]) ? 0 : 1);
                int receiverIndex = (senderIndex == 0) ? 1 : 0;

                if (message.equalsIgnoreCase("END")) {
                    System.out.println("Client " + (senderIndex + 1) + " has ended the chat.");
                    break;
                }

                System.out.println("Received from client " + (senderIndex + 1) + ": " + message);
                String response = "Client " + (senderIndex + 1) + ": " + message;
                mysocket.send(new DatagramPacket(response.getBytes(), response.length(), clientAddresses[receiverIndex], clientPorts[receiverIndex]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mysocket.close();
        }
    }

    public static void main(String[] args) {
        new UDPChatServer();
    }
}
