import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost"; // Server's hostname
        int port = 12345;          // Server's port

        try (Socket socket = new Socket(host, port)) {
            // Create input and output streams for the socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            System.out.println("Enter messages to send to the server (type 'exit' to quit):");
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("Echo from server: " + in.readLine());

                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            

        }
    }
}