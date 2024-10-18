import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage:   
 java TCPClient <server IP> <server port>");
            return;
        }

        String serverIP = args[0];
        int port = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(serverIP, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   

                PrintWriter out = new PrintWriter(new   
 BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                BufferedReader reader = new BufferedReader(new   