import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 12345; // Port to listen on

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Create input and output streams for the client connection
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String inputLine;
                // Read client input and echo it back
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    out.println("Echo: " + inputLine);
                }

                // Close the client socket
                clientSocket.close();
                System.out.println("Client disconnected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
