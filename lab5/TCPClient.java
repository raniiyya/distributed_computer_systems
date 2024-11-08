import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws   
 UnknownHostException {
        InetAddress address = InetAddress.getByName("localhost");
        try (Socket socket = new Socket(address, TCPMultithreading.PORT)) {
            System.out.println("Client with socket " + socket.getLocalPort() + " has started!");

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));   


            Scanner keyboardLine = new Scanner(System.in);
            String line;

            while ((line = keyboardLine.nextLine()) != null) {
                out.println(line); 

                String serverResponse = in.readLine(); 
                if (serverResponse != null) {
                    System.out.println("Server: " + serverResponse);
                }
            }

            keyboardLine.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}