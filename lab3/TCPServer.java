import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage:   
 java TCPServer <port number>");   

            return;
        }

        int port = Integer.parseInt(args[0]);

        ServerSocket s = new ServerSocket(port);
        System.out.println("Server started on IP " + s.getLocalAddress().getHostAddress() + ", Port: " + port);

        try {
            while (true) {
                Socket clientSocket = s.accept();
                System.out.println("Connection accepted: " + clientSocket);
                new ClientHandler(clientSocket).start();
            }
        } finally {
            s.close();
        }
    }
}

class ClientHandler extends Thread {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override   

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new   
 BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

            while (true) {
                String   
 command = in.readLine();
                if (command == null) {
                    break;
                }

                String response = handleCommand(command);
                out.println(response);

                if (command.equals("DROP")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private   
 String handleCommand(String command) {
        switch (command) {
            case "STATS":
                return calculateTextStats(readTextFromClient(in));
            case "ANAGRAM":
                return generateAnagram(readTextFromClient(in));
            case "DROP":
                return "Connection closed";
            default:
                return "Invalid command";
        }
    }

    private String readTextFromClient(BufferedReader in) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null && !line.equals("END")) {
            textBuilder.append(line).append("\n");
        }
        return textBuilder.toString().trim();
    }

    private String calculateTextStats(String text) {
        int lowercaseCount = 0, uppercaseCount = 0, digitCount = 0, otherCount = 0;
        for (char c : text.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowercaseCount++;
            } else if (Character.isUpperCase(c)) {
                uppercaseCount++;
            } else if (Character.isDigit(c)) {
                digitCount++;   

            } else   
 {
                otherCount++;
            }
        }
        return String.format("Lowercase: %d, Uppercase: %d, Digits: %d, Other: %d", lowercaseCount, uppercaseCount, digitCount, otherCount);
    }

    private String generateAnagram(String text) {
    char[] chars = text.toCharArray();
    List<Character> charList = new ArrayList<>();
    for (char c : chars) {
        charList.add(c);
    }
    Collections.shuffle(charList);
    StringBuilder 
 anagram = new StringBuilder();
    for (char c : charList) {
        anagram.append(c);
    }
    return anagram.toString();
}