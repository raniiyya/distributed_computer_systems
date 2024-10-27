import java.net.*;
import java.io.*;

public class UDPChatClient {
    private DatagramSocket mysocket;
    private InetAddress hostAddress;
    private byte[] inbuf = new byte[1000];
    private byte[] outbuf;
    private DatagramPacket dp;

    public UDPChatClient() {
        try {
            mysocket = new DatagramSocket();
            hostAddress = InetAddress.getByName("localhost");
            mysocket.connect(hostAddress, UDPChatServer.INPORT);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String outMessage;

            while (true) {
                System.out.print("Enter message (or 'END' to quit): ");
                outMessage = br.readLine();
                outbuf = outMessage.getBytes();
                mysocket.send(new DatagramPacket(outbuf, outbuf.length, hostAddress, UDPChatServer.INPORT));

                if (outMessage.equalsIgnoreCase("END")) {
                    break;
                }

                dp = new DatagramPacket(inbuf, inbuf.length);
                mysocket.receive(dp);
                String message = new String(dp.getData(), 0, dp.getLength());
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            mysocket.close();
        }
    }

    public static void main(String[] args) {
        new UDPChatClient();
    }
}
