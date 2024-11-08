import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPMultithreading {

    public static final int PORT = 7777;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PORT);   

            serverSocket.setReuseAddress(true);
            System.out.println("The   
 server is up!");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());   

 
 and start a new thread for each client
                new Thread(new ServerThread(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();   

                }
            }
        }
    }
}