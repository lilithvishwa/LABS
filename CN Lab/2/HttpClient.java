import java.io.*;
import java.net.*;
public class HttpClient {
 public static void main(String[] args) {
 String host = "example.com";
 try (Socket socket = new Socket(host, 80)) {
 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
 // Send HTTP GET request
 out.println("GET / HTTP/1.1");
 out.println("Host: " + host);
 out.println("Connection: close");
 out.println(); // End of headers
 
 // Read and print response
 String responseLine;
 while ((responseLine = in.readLine()) != null) {
 System.out.println(responseLine);
 }
 } catch (IOException e) {
    e.printStackTrace();
 }
 }
}

